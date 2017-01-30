package javax.observer.tck;

/**
 * Created by hendrikebbers on 11.11.16.
 */
public class BadImplementationException extends Exception {

    public BadImplementationException(String message) {
        super(message);
    }

    public BadImplementationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadImplementationException(Throwable cause) {
        super(cause);
    }
}
