package ua.nure.balagura.SummaryTask4.exception;

/**
 * An exception that provides information on an application error.
 * 
 */
public class AppException extends Exception  {

	private static final long serialVersionUID = -6310452963958515982L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}
}
