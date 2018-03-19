package pl.karolpat.exception;

public class NonuniqueEmailException extends Exception{

	private static final long serialVersionUID = 3773137172L;

	public NonuniqueEmailException() {
		super();
	}
	
	public NonuniqueEmailException(String message) {
		super(message);
	}
}
