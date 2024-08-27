package rpgde;

import java.security.PrivilegedActionException;

/**
 * @author Peter Dragicevic
 */
public class PathException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5210591614104989366L;
	private final String path;

	/**
	 * Constructs a new throwable with {@code null} as its detail message.
	 * The cause is not initialized, and may subsequently be initialized by a
	 * call to {@link #initCause}.
	 *
	 * <p>The {@link #fillInStackTrace()} method is called to initialize
	 * the stack trace data in the newly created throwable.
	 *
	 * @param path - Path of the Exception
	 */
	public PathException(String path) {
		super();
		this.path = path;
	}

	/**
	 * Constructs a new throwable with the specified detail message.  The
	 * cause is not initialized, and may subsequently be initialized by
	 * a call to {@link #initCause}.
	 *
	 * <p>The {@link #fillInStackTrace()} method is called to initialize
	 * the stack trace data in the newly created throwable.
	 *
	 * @param message the detail message. The detail message is saved for
	 * later retrieval by the {@link #getMessage()} method.
	 * @param path - Path of the Exception
	 */
	public PathException(String message, String path) {
		super(message);
		this.path = path;
	}

	/**
	 * Constructs a new throwable with the specified detail message and
	 * cause.  <p>Note that the detail message associated with
	 * {@code cause} is <i>not</i> automatically incorporated in
	 * this throwable's detail message.
	 *
	 * <p>The {@link #fillInStackTrace()} method is called to initialize
	 * the stack trace data in the newly created throwable.
	 *
	 * @param message the detail message (which is saved for later retrieval
	 * by the {@link #getMessage()} method).
	 * @param path - Path of the Exception
	 * @param cause the cause (which is saved for later retrieval by the
	 * {@link #getCause()} method).  (A {@code null} value is
	 * permitted, and indicates that the cause is nonexistent or
	 * unknown.)
	 */
	public PathException(String message, String path, Throwable cause) {
		super(message, cause);
		this.path = path;
	}

	/**
	 * Constructs a new throwable with the specified cause and a detail
	 * message of {@code (cause==null ? null : cause.toString())} (which
	 * typically contains the class and detail message of {@code cause}).
	 * This constructor is useful for throwables that are little more than
	 * wrappers for other throwables (for example, {@link
	 * PrivilegedActionException}).
	 *
	 * <p>The {@link #fillInStackTrace()} method is called to initialize
	 * the stack trace data in the newly created throwable.
	 *
	 * @param path - Path of the Exception
	 * @param cause the cause (which is saved for later retrieval by the
	 * {@link #getCause()} method).  (A {@code null} value is
	 * permitted, and indicates that the cause is nonexistent or
	 * unknown.)
	 */
	public PathException(String path, Throwable cause) {
		super(cause);
		this.path = path;
	}

	/**
	 * Gets the Path
	 *
	 * @return - Path of the Exception
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Returns the detail message string of this throwable.
	 *
	 * @return the detail message string of this {@code Throwable} instance
	 * (which may be {@code null}).
	 */
	@Override
	public String getMessage() {
		return super.getMessage() + " | Path: " + (this.getPath() == null ? "null" : this.getPath());
	}
}
