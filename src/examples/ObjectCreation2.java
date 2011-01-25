package examples;

import java.lang.reflect.InvocationTargetException;

import org.jgroups.Channel;
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
		int i;
		
		channel.connect("test");
		channel.setOpt(Channel.LOCAL, false);
		
		RemoteObjectSystem OSystem = new RemoteObjectSystem("TestSystem", channel);
		
		OSystem.getAllRemoteObjects();
		
		Thread.sleep(2000);
		
		for (i = 0 ; i < 30 ; i++) {
			
			Character c = (Character)OSystem.getRemoteObject("Toto1");
			if( c != null)
				System.out.println("[" + Thread.currentThread().getId() + "] Character Name : " + c.getName());
			else
				System.out.println("[" + Thread.currentThread().getId() + "] Object Toto1 not found !!");
				
			Thread.sleep(500);
			
		}
		
		OSystem.stoppedThread();
		
	}

}
