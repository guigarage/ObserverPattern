package javax.observer.tck;

/**
 * Created by hendrikebbers on 11.11.16.
 */
public class BadImplementationException extends Exception {

    private static final long serialVersionUID = 3516709693804131141L;

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
