package classes.commands;

import classes.RemoteCallData;

public class RPCCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5158774263437377537L;
	private String objectName;
	private String methodName;
	private RemoteCallData remoteCallData;
	
	public RPCCommand(String remoteObjectName, String methodName2,
			RemoteCallData rcd) {
		super();
		objectName = remoteObjectName;
		methodName = methodName2;
		remoteCallData = rcd;
		this.setIsRPC(true);
	}
	
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getMethodName() {
		return methodName;
	}
	
	public String toString() {
		return "Obj Name : " + objectName + " methodName : " + methodName;
	}

	public void setRemoteCallData(RemoteCallData remoteCallData) {
		this.remoteCallData = remoteCallData;
	}

	public RemoteCallData getRemoteCallData() {
		return remoteCallData;
	}
	
}
