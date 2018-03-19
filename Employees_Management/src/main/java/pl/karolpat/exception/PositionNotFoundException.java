package pl.karolpat.exception;

public class PositionNotFoundException extends Exception{

	private static final long serialVersionUID = 13762145515578L;
	
	public PositionNotFoundException() {
		super();
	}
	
	public PositionNotFoundException(String message){
		super(message);
	}

}
