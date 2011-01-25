package classes;

import org.jgroups.JChannel;

import examples.Character;
import examples.StringRemoteData;

public class RemoteObjectSystem {

	private JChannel channel;
	private String name;
	
	public RemoteObjectSystem(String systemName, JChannel channel) {
		this.channel = channel;
		name = systemName;
	}

	public void CallRemoteObjectMethod(String remoteObjectName, String methodName, StringRemoteData srd) {
		
		
		
	}

	public Character getRemoteObject(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public static RemoteObjectSystem getSystem() {
		// TODO Auto-generated method stub
		return null;
	}

	public void createRemoteObject(Character c, String string) {
		// TODO Auto-generated method stub
		
	}

}
