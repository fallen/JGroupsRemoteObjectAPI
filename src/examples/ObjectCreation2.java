package examples;

import java.lang.reflect.InvocationTargetException;

import org.jgroups.ChannelException;
import org.jgroups.JChannel;

import classes.RemoteObjectSystem;

public class ObjectCreation2 {

	/**
	 * @param args
	 * @throws ChannelException 
	 * @throws InterruptedException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
	public static void main(String[] args) throws ChannelException, InterruptedException, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		
		JChannel channel = new JChannel("udp.xml");
		channel.connect("test");
		
		RemoteObjectSystem OSystem = new RemoteObjectSystem("TestSystem", channel);
		
		Thread.sleep(5000);
		
		Character c = (Character)OSystem.getRemoteObject("Toto1");
		
		System.out.println("Character c Name : " + c.getName());
		
		Thread.sleep(7000);
		
		Character c2 = (Character)OSystem.getRemoteObject("Toto1");
		
		System.out.println("Character c2 Name : " + c2.getName());

	}

}
