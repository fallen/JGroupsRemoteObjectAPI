package classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.jgroups.ChannelClosedException;
import org.jgroups.ChannelNotConnectedException;
import org.jgroups.JChannel;
import org.jgroups.Message;

import classes.commands.Command;
import classes.commands.CreateObjectCommand;
import classes.commands.DeleteObjectCommand;
import classes.commands.RPCCommand;
import classes.commands.UpdateObjectCommand;

import interfaces.IRemotableObject;

public class RemoteObjectSystem {

	private JChannel channel;
	private String name;
	private HashMap<String, IRemotableObject> remotableObjects;
	
	public RemoteObjectSystem(String systemName, JChannel channel) {
		this.channel = channel;
		name = systemName;
		remotableObjects = new HashMap<String, IRemotableObject>();
		
		System.out.println("Launching JGroups receiver Thread...");
		Runnable  r = new JGroupsThread(channel, this);
		Thread t = new Thread(r);
		t.start();
		System.out.println("OK");
		
	}

	public void CallRemoteObjectMethod(String remoteObjectName, String methodName, RemoteCallData rcd) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, ChannelNotConnectedException, ChannelClosedException {
		// Now we call the method on our local Object
		CallLocalMethod(remoteObjectName, methodName, rcd);
		
		RPCCommand c = new RPCCommand(remoteObjectName, methodName, rcd);
		
		// Then we send CALL commands to all JGroups members
		Message mess = new Message(null, null, c);
		channel.send(mess);
		
	}
	
	public void CallLocalMethod(String objectName, String methodName, RemoteCallData rcd) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		IRemotableObject o = remotableObjects.get(objectName);
		
		// Now we call the method on our local Object
		Method m = o.getClass().getDeclaredMethod(methodName, RemoteCallData.class);
		m.invoke(o, rcd);
	}

	public IRemotableObject getRemoteObject(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	public void createRemoteObject(IRemotableObject o, String objectName) throws ChannelNotConnectedException, ChannelClosedException {
		this.addNewRemotableObject(o,objectName);
		
		CreateObjectCommand c = new CreateObjectCommand(o.getObjectName(), o.getClass().toString());
		
		Message mess = new Message(null, null, c);
		
		channel.send(mess);
		
	}
	
	public void addNewRemotableObject( IRemotableObject o, String name){
		remotableObjects.put(name, o);
	}
	
	public void deleteRemotableObject(String name) throws ChannelNotConnectedException, ChannelClosedException
	{
		deleteLocalObject(name);
		
		DeleteObjectCommand c = new DeleteObjectCommand(name);
		
		Message mess = new Message(null, null, c);
		
		channel.send(mess);
	}
	
	public void deleteLocalObject(String name) {
		remotableObjects.remove(name);
	}
	
	public void updateRemotableObject(String name) throws ChannelNotConnectedException, ChannelClosedException {
		IRemotableObject o = remotableObjects.get(name);
		updateLocalObject(o, name);
		
		UpdateObjectCommand c = new UpdateObjectCommand(o, name);
		
		Message mess = new Message(null, null, c);
		
		channel.send(mess);
	}
	
	public void updateLocalObject(IRemotableObject o, String name)
	{	
		this.remotableObjects.remove(name);
		this.remotableObjects.put(name, o);
	}

	public void parseCommand(Command c) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
		
		if (c == null)
			return;
		
		if (c.getIsRPC()) {
			RPCCommand rpc = (RPCCommand)c;
			CallLocalMethod(rpc.getObjectName(), rpc.getMethodName(), rpc.getRemoteCallData());
		}
		
		if (c.getIsCreateObject()) {
			CreateObjectCommand object = (CreateObjectCommand)c;
			Class cl = Class.forName(object.getClassName());
			java.lang.reflect.Constructor co = cl.getConstructor();
			IRemotableObject remotableObject = (IRemotableObject) co.newInstance();
			addNewRemotableObject( remotableObject, object.getObjectName());
		}
		
		if (c.getIsDeleteObject()) {
			DeleteObjectCommand object = (DeleteObjectCommand)c;
			deleteLocalObject(object.getObjectName());
		}
		
		if (c.getIsUpdateObject()) {
			UpdateObjectCommand object = (UpdateObjectCommand)c;
			updateLocalObject(object.getObject(), object.getObjectName());
		}
	}

}
