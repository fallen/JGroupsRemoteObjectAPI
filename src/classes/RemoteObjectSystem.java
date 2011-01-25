package classes;

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

	public void CallRemoteObjectMethod(String remoteObjectName, String methodName, StringRemoteData srd) {
		
		
		
	}

	public Character getRemoteObject(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	public void createRemoteObject(IRemotableObject o, String objectName) {
		remotableObjects.put(objectName, o);
		
	}

}
