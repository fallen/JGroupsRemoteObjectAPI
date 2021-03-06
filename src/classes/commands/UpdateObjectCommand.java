package classes.commands;

import interfaces.IRemotableObject;
import classes.RemoteCallData;

public class UpdateObjectCommand extends Command{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 468303898796460946L;
	private IRemotableObject object;
	private String objectName;
	
	public UpdateObjectCommand(IRemotableObject object, String objectName)
	{
		super();
		this.object = object;
		this.objectName = objectName;
		this.setIsUpdateObject(true);
	}

	public IRemotableObject getObject() {
		return object;
	}

	public void setObject(IRemotableObject object) {
		this.object = object;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	public String toString() {
		return "UpdateObject Command on objectName=" + objectName;
	}

}
