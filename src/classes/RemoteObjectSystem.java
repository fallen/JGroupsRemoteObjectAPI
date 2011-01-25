package classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Vector;

import org.jgroups.Address;
import org.jgroups.ChannelClosedException;
import org.jgroups.ChannelNotConnectedException;
import org.jgroups.JChannel;
import org.jgroups.Message;

import classes.commands.Command;
import classes.commands.CreateObjectCommand;
import classes.commands.DeleteObjectCommand;
import classes.commands.GetAllObjectsAnswerCommand;
import classes.commands.GetAllObjectsCommand;
import classes.commands.RPCCommand;
import classes.commands.UpdateObjectCommand;

import interfaces.IRemotableObject;

public class RemoteObjectSystem {

	private JChannel channel;
	private String name;
	private HashMap<String, IRemotableObject> remotableObjects;
	private Boolean waitingForAnswer = Boolean.valueOf(false);
	private Long blockedThread = new Long(Thread.currentThread().getId());
	
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
	
	public HashMap<String, IRemotableObject> getRemotableObjects() 
	{
		return remotableObjects;
		
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
		
		if (o == null) {
			System.out.println("Error object " + objectName + " not found !");
			return;
		}
		
		// Now we call the method on our local Object
		Method m = o.getClass().getDeclaredMethod(methodName, RemoteCallData.class);
		m.invoke(o, rcd);
	}

	public IRemotableObject getRemoteObject(String string) {
		return remotableObjects.get(string);
	}


	public void createRemoteObject(IRemotableObject o, String objectName) throws ChannelNotConnectedException, ChannelClosedException {
		this.addNewRemotableObject(o,objectName);
		o.setObjectName(objectName);
		
		CreateObjectCommand c = new CreateObjectCommand(objectName, o.getClass().getName());
		Message mess = new Message(null, null, c);
		channel.send(mess);
		
		UpdateObjectCommand c2 = new UpdateObjectCommand(o, objectName);
		mess = new Message(null, null, c2);
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

	public void parseCommand(Command c) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException, ChannelNotConnectedException, ChannelClosedException {
		
		System.out.println("This == " + this);
		
		if (c == null)
			return;
		
		if (c.getIsGetAllObjectsAnswer()) {
			
			unBlockThread();
			GetAllObjectsAnswerCommand co = (GetAllObjectsAnswerCommand)c;
			remotableObjects = co.getObjects();
			
		} else if (c.getIsGetAllObjects()) {
			
			GetAllObjectsCommand co = (GetAllObjectsCommand)c;
			Address replyTo = co.getAddr();
			
			GetAllObjectsAnswerCommand answer = new GetAllObjectsAnswerCommand(remotableObjects);
			
			Message m = new Message(replyTo, null, answer);
			channel.send(m);
			
		} else if (c.getIsRPC()) {
			
			RPCCommand rpc = (RPCCommand)c;
			CallLocalMethod(rpc.getObjectName(), rpc.getMethodName(), rpc.getRemoteCallData());
			
		} else if (c.getIsCreateObject()) {
			
			CreateObjectCommand object = (CreateObjectCommand)c;
			System.out.println("We are searching for Class : " + object.getClassName());
			Class<?> cl = Class.forName(object.getClassName());
			Constructor co = cl.getConstructor();
			IRemotableObject remotableObject = (IRemotableObject) co.newInstance();
			addNewRemotableObject( remotableObject, object.getObjectName());
			
		} else if (c.getIsDeleteObject()) {
			
			DeleteObjectCommand object = (DeleteObjectCommand)c;
			deleteLocalObject(object.getObjectName());
			
		} else if (c.getIsUpdateObject()) {
			
			UpdateObjectCommand object = (UpdateObjectCommand)c;
			updateLocalObject(object.getObject(), object.getObjectName());
			
		} else 
			System.out.println("[" + Thread.currentThread().getId() + "] Error Command unknown !!");
	}

	public void getAllRemoteObjects() throws ChannelNotConnectedException, ChannelClosedException {
		
		Vector<Address> addresses = channel.getView().getMembers();
		Address friend = null;
		Address me = channel.getLocalAddress();
		int i;
		
		if (addresses.size() == 0)
			return;
		
		for (i = 0 ; i < addresses.size() ; i++)
			if (!addresses.elementAt(i).equals(me))
				friend = addresses.elementAt(i);
		
		if (friend == null)
			return;
		
		GetAllObjectsCommand c = new GetAllObjectsCommand(me);
		Message m = new Message(friend, null, c);
		
		waitingForAnswer = Boolean.valueOf(true);
		channel.send(m);
		
		System.out.println("Avant de bloquer : this == " + this);
		
		blockThread();
		

	}
	
	void blockThread() {
		Long id = Thread.currentThread().getId();
		System.out.println("["+ id +"] We block this Thread");
		blockedThread = id;
		while (waitingForAnswer.booleanValue()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("["+ id +"] We leave the while");
	}
	
	void unBlockThread() {
		System.out.println("The thread " + Thread.currentThread().getId() + " has unblocked the Thread " + blockedThread);
		waitingForAnswer = Boolean.valueOf(false);
	}

}
