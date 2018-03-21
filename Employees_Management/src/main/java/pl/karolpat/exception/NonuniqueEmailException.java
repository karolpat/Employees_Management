package pl.karolpat.exception;

@SuppressWarnings("serial")
public class NonuniqueEmailException extends RuntimeException {

	private static final String MESSAGE = "Given email is already present.";

	public NonuniqueEmailException() {
		super(MESSAGE);
	}
}
