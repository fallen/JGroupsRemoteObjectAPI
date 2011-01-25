package classes.commands;

import org.jgroups.Address;

public class GetAllObjectsCommand extends Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7362570202556485156L;
	private Address addr;
	
	
	public GetAllObjectsCommand(Address a) {
		super();
		setAddr(a);
		setIsGetAllObjects(true);
	}


	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public Address getAddr() {
		return addr;
	}

	public String toString() {
		return "GetAllObjects Command";
	}
	
}
