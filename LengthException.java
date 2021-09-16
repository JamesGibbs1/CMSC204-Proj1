package Assignments.Fall2020.Assignment1.JGibbs;

@SuppressWarnings("serial")
public class LengthException extends Exception 
{
	
	public LengthException() {
		super("The password must be at least 6 characters long");
	}
	
	public LengthException(String message) {
		super(message);
	}
	
	
}
