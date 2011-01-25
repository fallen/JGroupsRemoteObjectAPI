package examples;

import classes.RemoteCallData;
import interfaces.IRemotableObject;

public class Character implements IRemotableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1124806646037902450L;

	private String name = new String();
	private String objectName = new String();
	
	public Character() {
		
	}
	
	public Character(String objectName) {
		setName(objectName);
	}

	@Override
	public String getObjectName() {
		return objectName;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setName(RemoteCallData rcd) {
		StringRemoteData srd = (StringRemoteData)rcd;
		if (srd == null)
			System.out.println("ERREUR : srd == null");
		
		this.name = srd.getData();
	}

	public String getName() {
		return name;
	}

	@Override
	public void setObjectName(String name) {
		objectName = name;
	}

}
