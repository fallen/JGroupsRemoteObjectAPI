package classes;

import java.lang.reflect.InvocationTargetException;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import classes.commands.Command;
import classes.commands.RPCCommand;

public class JGroupsThread extends ReceiverAdapter implements Runnable  {

	private JChannel channel;
	private Boolean stopped = false;
	private RemoteObjectSystem OSystem;
	
    public void receive(Message msg) {
    	
    	if (msg.getSrc() == channel.getLocalAddress()) {
    		System.out.println("We've just received our own packet, we DROP !");
    		return ;
    	}
    	
    	Command c = (Command) msg.getObject();
    	
        System.out.println( c );
        
        try {
			OSystem.parseCommand(c);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
        
    }
	
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }
    
	public JGroupsThread(JChannel c, RemoteObjectSystem remoteObjectSystem) {
		channel = c;
		OSystem = remoteObjectSystem;
	}
	
	@Override
	public void run() {
         channel.setReceiver(this);
		 while(!stopped) {
	            
	        }
	}

}
