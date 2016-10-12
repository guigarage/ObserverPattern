package com.guigarage.observer;

import java.util.Optional;

public interface ValueChangeEvent<V> {

    Observable<V> getSource();

    Optional<V> oldValue();

    Optional<V> newValue();

    default V getOldValue() {
        return oldValue().get();
    }

    default V getNewValue() {
        return newValue().get();
    }
}
