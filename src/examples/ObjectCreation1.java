package examples;

import java.lang.reflect.InvocationTargetException;

import org.jgroups.ChannelException;
import org.jgroups.JChannel;

import classes.RemoteObjectSystem;

public class ObjectCreation1 {

	/**
	 * @param args
	 * @throws ChannelException 
	 * @throws InterruptedException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws ChannelException, InterruptedException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		
		JChannel channel = new JChannel("udp.xml");
		channel.connect("test");
		
		RemoteObjectSystem OSystem = new RemoteObjectSystem("TestSystem", channel);
		
		Character c = new Character("Toto");
		
		OSystem.createRemoteObject(c, "Toto1");
		
		System.out.println("Character c Name : " + c.getName());
		
		c.setName("Paul");
		
		Thread.sleep(7000);
		
		StringRemoteData srd = new StringRemoteData("Paul2");
		
		OSystem.CallRemoteObjectMethod("Toto1", "setName", srd);
		
		System.out.println("Character c Name : " + c.getName());
		
		Character c2 = (Character) OSystem.getRemoteObject("Toto1");
		
		System.out.println("Character c Name : " + c.getName());
		System.out.println("Character c2 Name : " + c2.getName());
		
	}

}
