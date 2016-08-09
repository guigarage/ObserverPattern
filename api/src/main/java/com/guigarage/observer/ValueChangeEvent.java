package com.guigarage.observer;

public interface ValueChangeEvent<V> {

    Observable getSource();

    V getOldValue();

    V getNewValue();
}
