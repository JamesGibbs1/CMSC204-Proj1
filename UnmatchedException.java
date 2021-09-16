package Assignments.Fall2020.Assignment1.JGibbs;

@SuppressWarnings("serial")
public class UnmatchedException extends Exception {

	public UnmatchedException() {
		super("The passwords do not match.");
	}
	
	public UnmatchedException(String message) {
		super(message);
	}

}
