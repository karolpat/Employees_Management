package pl.karolpat.exception;

public class EmployeeNotFoundException extends Exception {

	private static final long serialVersionUID = 12123123412345L;
	
	
	public EmployeeNotFoundException() {
		super();
	}
	
	public EmployeeNotFoundException(String message){
		super(message);
	}

}
