package Assignments.Fall2020.Assignment1.JGibbs;

@SuppressWarnings("serial")
public class NoSpecialCharacterException extends Exception {
	

	public NoSpecialCharacterException() {
		super("The password must contain at least one special character.");
	}
	
	public NoSpecialCharacterException(String message){
		super(message);
	}
}
