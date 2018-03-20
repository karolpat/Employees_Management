package pl.karolpat.exception;

public class PositionExistsException extends Exception{

	private static final long serialVersionUID = -7153456088065L;

	public PositionExistsException() {
		super();
	}
	
	public PositionExistsException(String message) {
		super(message);
	}
}
