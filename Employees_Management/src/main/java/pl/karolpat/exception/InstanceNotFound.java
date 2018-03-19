package pl.karolpat.exception;

public class InstanceNotFound extends Exception {

	private static final long serialVersionUID = 12123123412345L;
	
	
	public InstanceNotFound() {
		super();
	}
	
	public InstanceNotFound(String message){
		super(message);
	}

}
