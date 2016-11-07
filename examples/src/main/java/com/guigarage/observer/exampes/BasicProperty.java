package com.guigarage.observer.exampes;


import javax.observer.Property;

public class BasicProperty<V> extends BasicObservable<V> implements Property<V> {

    @Override
    public void set(V value) {
        updateValue(value);
    }
}
