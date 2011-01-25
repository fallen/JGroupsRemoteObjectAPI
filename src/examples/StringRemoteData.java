package examples;

import classes.RemoteCallData;

public class StringRemoteData extends RemoteCallData {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1288415964070533138L;
	private String data;
	
	public StringRemoteData(String s) {
		super();
		setData(s);
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getData() {
		return data;
	}
	
}
