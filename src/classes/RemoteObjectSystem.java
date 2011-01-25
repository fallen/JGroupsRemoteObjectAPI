package classes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.jgroups.JChannel;

import examples.Character;
import examples.StringRemoteData;
import interfaces.IRemotableObject;

public class RemoteObjectSystem {

	private JChannel channel;
	private String name;
	private HashMap<String, IRemotableObject> remotableObjects;
	
	public RemoteObjectSystem(String systemName, JChannel channel) {
		this.channel = channel;
		name = systemName;
		remotableObjects = new HashMap<String, IRemotableObject>();
	}

	public void CallRemoteObjectMethod(String remoteObjectName, String methodName, RemoteCallData rcd) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		IRemotableObject o = remotableObjects.get(remoteObjectName);
		
		// Now we call the method on our local Object
		Method m = o.getClass().getDeclaredMethod(methodName, RemoteCallData.class);
		m.invoke(o, rcd);
		
		// Then we send CALL commands to all JGroups members
		
	}

	public IRemotableObject getRemoteObject(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	public void createRemoteObject(IRemotableObject o, String objectName) {
		remotableObjects.put(objectName, o);
		
	}

}
