package com.guigarage.observer;


import javax.observer.Property;

public class BasicProperty<V> extends BasicObservable<V> implements Property<V> {

    @Override
    public void setValue(V value) {
        updateValue(value);
    }
}
