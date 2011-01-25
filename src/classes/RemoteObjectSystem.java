package classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.jgroups.Address;
import org.jgroups.ChannelClosedException;
import org.jgroups.ChannelNotConnectedException;
import org.jgroups.JChannel;
import org.jgroups.Message;

import classes.commands.Command;
import classes.commands.RPCCommand;

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


	public void createRemoteObject(IRemotableObject o, String objectName) {
		remotableObjects.put(objectName, o);
		
	}

	public void parseCommand(Command c) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		
		if (c == null)
			return;
		
		if (c.getIsRPC()) {
			RPCCommand rpc = (RPCCommand)c;
			CallLocalMethod(rpc.getObjectName(), rpc.getMethodName(), rpc.getRemoteCallData());
		}
		
	}

}
