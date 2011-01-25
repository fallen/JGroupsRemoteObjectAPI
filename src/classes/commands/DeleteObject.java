package classes.commands;

public class DeleteObject extends Command{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1137173305761692866L;
	private String objectName;
	private String className;
	
	public DeleteObject(String object, String className)
	{
		this.objectName = object;
		this.className = className;
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
