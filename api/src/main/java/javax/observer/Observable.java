package javax.observer;

import java.util.Optional;

/**
 * This interfaces provides general support of the observer pattern. Implementations / Instances might
 * work as a wrapper around a basic variable and will fire change events whenever the value of the variable
 * has changed.
 * @author Hendrik Ebbers
 */
public interface Observable<V> {

    /**
     * Returns the current value.
     * @return the value
     */
    V getValue();

    /**
     * Returns an {@link Optional} that contains the current value or {@code null}. This is a
     * convenience method that can be used to acces the value by using a fluent API based on
     * the {@link Optional} API.
     * @return an {@link Optional} that contains the current value or {@code null}
     */
    default Optional<V> value() {
        return Optional.ofNullable(getValue());
    }

    Subscription onChanged(ValueChangeListener<? super V> listener);
}
