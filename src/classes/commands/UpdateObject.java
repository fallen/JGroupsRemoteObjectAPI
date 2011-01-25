package classes.commands;

import interfaces.IRemotableObject;
import classes.RemoteCallData;

public class UpdateObject extends Command{
	
	private IRemotableObject object;
	private String objectName;
	
	public UpdateObject(IRemotableObject object, String objectname)
	{
		this.object = object;
		this.objectName = objectname;
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
	
	

}
