package exceptions;

/**
 * An exception that is thrown when an object cannot be built.
 */
public class BuildObjectException extends Exception {

    /**
     * Constructs a new BuildObjectException with the specified detail message.
     *
     * @param message the detail message
     */
    public BuildObjectException(String message)
    {
        super(message);
    }

    public BuildObjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
