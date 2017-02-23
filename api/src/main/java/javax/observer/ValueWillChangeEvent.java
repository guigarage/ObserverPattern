package javax.observer;

import java.util.Optional;

/**
 * Created by hendrikebbers on 30.01.17.
 */
public interface ValueWillChangeEvent<V> {

    Observable<V> getObservable();

    default Optional<V> newValue() {
        return Optional.ofNullable(getNewValue());
    }

    V getNewValue();

    /**
     * If this method is called the change will be removed and the value of the observable will not be set
     * to the new value.
     */
    void veto();
}
