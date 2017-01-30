package javax.observer.binding;

import javax.observer.Observable;
import javax.observer.Subscription;
import java.util.function.Consumer;

/**
 * This interface defines objects that can be bound to an {@link Observable}. By doing so any change of
 * the value of the {@link Observable} will be propagated to this object.
 * @param <T> type of the value
 */
public interface Bindable<T> {

    /**
     * Creates an unidirectional binding by binding this Bindable to the given {@link Observable}.
     * By doing so any change of the value of the {@link Observable} will be propagated to this object.
     * @param observable the {@link Observable} to that this instance will be bound.
     * @return a {@link Subscription} to unbind the binding that is created by calling this method
     */
    Subscription to(Observable<T> observable);

    /**
     * Since the calling a binding can result in an error (for example setting the new value can throw
     * an exception), it's best practice to provide an error handler to handle such an exception.
     * @param handler the error handler
     * @return this object. This can be used to create a small fluent API
     */
    ConvertableBindable<T> withErrorHandler(Consumer<Throwable> handler);

}
