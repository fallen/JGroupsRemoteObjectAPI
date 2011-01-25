package classes.commands;

public class DeleteObjectCommand extends Command{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1137173305761692866L;
	private String objectName;
	
	public DeleteObjectCommand(String object)
	{
		super();
		this.objectName = object;
		this.setIsUpdateObject(true);
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
}
