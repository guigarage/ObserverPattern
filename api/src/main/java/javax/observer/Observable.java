package javax.observer;

import java.util.Optional;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface Observable<V> {

    V get();

    default Optional<V> value() {
        return Optional.ofNullable(get());
    }

    Subscription onChanged(ValueChangeListener<? super V> listener);
}
