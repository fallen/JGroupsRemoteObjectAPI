package classes.commands;

import java.io.Serializable;

public abstract class Command implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -858244501261760484L;
	
	private Boolean isGetAllObjects;
	private Boolean isRPC;
	private Boolean isUpdateObject;
	private Boolean isCreateObject;
	private Boolean isDeleteObject;
	private Boolean isGetAllObjectsAnswer;
	
	protected Command() {
		isRPC = new Boolean(false);
		isUpdateObject = new Boolean(false);
		isCreateObject = new Boolean(false);
		isDeleteObject = new Boolean(false);
		isGetAllObjects = new Boolean(false);
		isGetAllObjectsAnswer = new Boolean(false);
	}
	
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

	public void setIsGetAllObjects(Boolean isGetAllObjects) {
		this.isGetAllObjects = isGetAllObjects;
	}

	public Boolean getIsGetAllObjects() {
		return isGetAllObjects;
	}
	
	public Boolean getIsGetAllObjectsAnswer() {
		return isGetAllObjectsAnswer;
	}
	
	public void setIsGetAllObjectsAnswer(Boolean isGetAllObjectsAnswer) {
		this.isGetAllObjectsAnswer = isGetAllObjectsAnswer;
	}

	
}
