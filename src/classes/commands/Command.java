package classes.commands;

public abstract class Command {
	public Boolean getIsRPC() {
		return isRPC;
	}
	public void setIsRPC(Boolean isRPC) {
		this.isRPC = isRPC;
	}
	public Boolean getIsUpdateObject() {
		return isUpdateObject;
	}
	public void setIsUpdateObject(Boolean isUpdateObject) {
		this.isUpdateObject = isUpdateObject;
	}
	public Boolean getIsCreateObject() {
		return isCreateObject;
	}
	public void setIsCreateObject(Boolean isCreateObject) {
		this.isCreateObject = isCreateObject;
	}
	public Boolean getIsDeleteObject() {
		return isDeleteObject;
	}
	public void setIsDeleteObject(Boolean isDeleteObject) {
		this.isDeleteObject = isDeleteObject;
	}
	private Boolean isRPC;
	private Boolean isUpdateObject;
	private Boolean isCreateObject;
	private Boolean isDeleteObject;
	
}
