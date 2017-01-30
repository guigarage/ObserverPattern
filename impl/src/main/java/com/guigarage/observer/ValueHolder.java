package com.guigarage.observer;

/**
 * Created by hendrikebbers on 30.01.17.
 */
public class ValueHolder<T> {

    T value;

    public ValueHolder() {
    }

    public ValueHolder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}