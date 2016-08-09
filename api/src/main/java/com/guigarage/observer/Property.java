package com.guigarage.observer;

public interface Property<V> extends Observable<V> {

    void set(V value);
}
