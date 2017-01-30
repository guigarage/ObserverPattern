package javax.observer;

import java.util.Optional;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface Observable<V> {

    V getValue();

    default Optional<V> value() {
        return Optional.ofNullable(getValue());
    }

    Subscription onChanged(ValueChangeListener<? super V> listener);
}
