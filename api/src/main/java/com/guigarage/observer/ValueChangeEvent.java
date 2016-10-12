package com.guigarage.observer;

import java.util.Optional;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface ValueChangeEvent<V> {

    Observable<V> getObservable();

    Optional<V> oldValue();

    Optional<V> newValue();

    default V getOldValue() {
        return oldValue().get();
    }

    default V getNewValue() {
        return newValue().get();
    }
}
