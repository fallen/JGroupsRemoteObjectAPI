package classes;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

public class JGroupsThread extends ReceiverAdapter implements Runnable  {

	private JChannel channel;
	private Boolean stopped = false;
	
    public void receive(Message msg) {
        String line=msg.getSrc() + ": " + msg.getObject();
        System.out.println(line);
    }
	
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }
    
	public JGroupsThread(JChannel c) {
		channel = c;
	}
	
	@Override
	public void run() {
         channel.setReceiver(this);
		 while(!stopped) {
	            
	        }
	}

}
