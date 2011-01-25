package examples;

import interfaces.IRemotableObject;

public class Character implements IRemotableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1124806646037902450L;

	private String name; 
	
	public Character(String objectName) {
		setName(objectName);
	}

	@Override
	public String getObjectName() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
