package grades;

/**
 * Exception that should be thrown if an invalid grade is passed in.
 *
 */
public class InvalidGradeException extends Exception {
	
	public InvalidGradeException() {}
	public InvalidGradeException(String message) {
		super(message);
	}
	
}