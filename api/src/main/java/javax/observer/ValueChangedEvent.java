package javax.observer;

import java.util.Optional;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface ValueChangedEvent<V> {

    Observable<V> getObservable();

    default Optional<V> value() {
        return Optional.ofNullable(getValue());
    }

    V getValue();
}
