package pl.karolpat.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

	private static final String MESSAGE = "There is no employee with such an ID.";

	public EmployeeNotFoundException() {
		super(MESSAGE);
	}

}
