package pl.karolpat.exception;

@SuppressWarnings("serial")
public class PositionExistsException extends RuntimeException {

	private static final String MESSAGE = "Position with given name already exists.";

	public PositionExistsException() {
		super(MESSAGE);
	}
}
