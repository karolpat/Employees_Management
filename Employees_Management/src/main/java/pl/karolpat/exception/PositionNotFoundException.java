package pl.karolpat.exception;

@SuppressWarnings("serial")
public class PositionNotFoundException extends RuntimeException{

	private static final String MESSAGE = "There is no position with a such ID.";
	
	public PositionNotFoundException(){
		super(MESSAGE);
	}

}
