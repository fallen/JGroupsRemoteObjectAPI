package classes.commands;

public class DeleteObject extends Command{
	
	private String objectName;
	private String className;
	
	public DeleteObject(String object, String classname)
	{
		this.objectName = object;
		this.className = classname;
		this.setIsUpdateObject(true);
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	
}
