package examples;

import org.jgroups.ChannelException;
import org.jgroups.JChannel;

import classes.RemoteObjectSystem;

public class ObjectCreation2 {

	/**
	 * @param args
	 * @throws ChannelException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws ChannelException, InterruptedException {
		
		JChannel channel = new JChannel("udp.xml");
		channel.connect("test");
		
		RemoteObjectSystem OSystem = new RemoteObjectSystem("TestSystem", channel);
		
		Thread.sleep(5000);
		
		Character c = (Character)OSystem.getRemoteObject("Toto1");
		
		StringRemoteData srd = new StringRemoteData(null);
		
		OSystem.CallRemoteObjectMethod("Toto1", "getName", srd);
		
		System.out.println("Le nom de l'objet Toto1 est : "+c.getName()+" en utilisant la méthode getRemoteObject");

	}

}
