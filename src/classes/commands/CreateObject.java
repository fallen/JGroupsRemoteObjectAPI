package classes.commands;

public class CreateObject extends Command{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1764970717744482926L;
	private String objectName;
	private String className;
	
	public CreateObject(String objectName,String className)
	{
		this.objectName = objectName;
		this.className = className;
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
