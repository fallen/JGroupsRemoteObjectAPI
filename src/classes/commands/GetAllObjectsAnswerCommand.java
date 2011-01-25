package classes.commands;

import java.util.HashMap;

import interfaces.IRemotableObject;

public class GetAllObjectsAnswerCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2178674541368686945L;
	private HashMap<String, IRemotableObject> objects;
	
	public GetAllObjectsAnswerCommand(HashMap<String, IRemotableObject> objs) {
		super();
		objects = objs;
		setIsGetAllObjectsAnswer(true);
	}
	
	public void setObjects(HashMap<String, IRemotableObject> objects) {
		this.objects = objects;
	}
	public HashMap<String, IRemotableObject> getObjects() {
		return objects;
	}
	
	public String toString() {
		return "GetAllObjectsAnswer Command";
	}
}
