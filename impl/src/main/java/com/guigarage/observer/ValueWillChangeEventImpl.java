package com.guigarage.observer;

import javax.observer.Observable;
import javax.observer.ValueWillChangeEvent;

public class ValueWillChangeEventImpl<V> implements ValueWillChangeEvent<V> {

    private final Observable<V> observable;

    private final V newValue;

    private boolean vetoCalled = false;

    public ValueWillChangeEventImpl(Observable<V> observable, V newValue) {
        this.observable = observable;
        this.newValue = newValue;
    }

    @Override
    public Observable<V> getObservable() {
        return observable;
    }

    @Override
    public V getNewValue() {
        return newValue;
    }

    @Override
    public void veto() {
        vetoCalled = true;
    }

    public boolean isVetoCalled() {
        return vetoCalled;
    }
}
