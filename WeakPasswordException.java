package Assignments.Fall2020.Assignment1.JGibbs;

@SuppressWarnings("serial")
public class WeakPasswordException extends Exception {

	public WeakPasswordException() {
		super("The password is OK but weak - it contains fewer than 10 characters");
	}
	
	public WeakPasswordException(String message) {
		super(message);
	}
	
	
}
