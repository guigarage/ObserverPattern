package com.guigarage.observer;

import java.util.Optional;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface ValueChangeEvent<V> {

    Observable<V> getObservable();

    default Optional<V> oldValue() {
        return Optional.ofNullable(getOldValue());
    }

    default Optional<V> newValue() {
        return Optional.ofNullable(getNewValue());
    }

    V getOldValue();

    V getNewValue();
}
