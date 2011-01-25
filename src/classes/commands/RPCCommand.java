package classes.commands;

import classes.RemoteCallData;

public class RPCCommand extends Command {

	private String objectName;
	private String methodName;
	private RemoteCallData arg;
	
	public RPCCommand(String remoteObjectName, String methodName2,
			RemoteCallData rcd) {
		objectName = remoteObjectName;
		methodName = methodName2;
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
	public void setArg(RemoteCallData arg) {
		this.arg = arg;
	}
	public RemoteCallData getArg() {
		return arg;
	}
	
	
	
}
