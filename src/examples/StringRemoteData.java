package examples;

import interfaces.RemoteCallData;

public class StringRemoteData implements RemoteCallData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1288415964070533138L;
	public String data;
	
	public StringRemoteData(String s) {
		data = s;
	}
	
}
