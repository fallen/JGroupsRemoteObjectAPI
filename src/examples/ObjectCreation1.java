package examples;

import org.jgroups.ChannelException;
import org.jgroups.JChannel;

import classes.RemoteObjectSystem;

public class ObjectCreation1 {

	/**
	 * @param args
	 * @throws ChannelException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws ChannelException, InterruptedException {
		// TODO Auto-generated method stub
		
		JChannel channel = new JChannel("udp.xml");
		channel.connect("test");
		
		RemoteObjectSystem OSystem = new RemoteObjectSystem("TestSystem", channel);
		
		Thread.sleep(2000);
		
		Character c = new Character("Toto");
		
		OSystem.createRemoteObject(c, "Toto1");
		
		System.out.println("Character Name : " + c.getName());
		
		c.setName("Paul");
		
		StringRemoteData srd = new StringRemoteData("Paul2");
		
		OSystem.CallRemoteObjectMethod("Toto", "setName", srd);
		
		Character c2 = (Character) OSystem.getRemoteObject("Toto1");
		
		
		
	}

}
