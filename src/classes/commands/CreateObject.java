package classes.commands;

public class CreateObject extends Command{
	
	private String objectName;
	private String className;
	
	public CreateObject(String objectname,String classname)
	{
		this.objectName = objectname;
		this.className = classname;
		this.setIsCreateObject(true);
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
