package com.guigarage.observer;

import javax.observer.Observable;
import javax.observer.Subscription;
import javax.observer.ValueChangedEvent;
import javax.observer.ValueChangedListener;
import javax.observer.ValueWillChangeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BasicObservable<V> implements Observable<V> {

    private V value;

    private final List<ValueChangedListener<? super V>> listeners = new CopyOnWriteArrayList<>();

    private Lock valueLock = new ReentrantLock();

    protected void updateValue(final V value) {
        if (this.value != value) {
            V oldValue = this.value;
            valueLock.lock();
            try {
                this.value = value;
            } finally {
                valueLock.unlock();
                fireChangeEvent(oldValue, this.value);
            }
        }
    }

    private void fireChangeEvent(final V oldValue, final V newValue) {
        final ValueChangedEvent<V> event = new ValueChangedEvent<V>() {
            @Override
            public Observable getObservable() {
                return BasicObservable.this;
            }

            @Override
            public V getValue() {
                return newValue;
            }
        };
        listeners.forEach(l -> l.valueChanged(event));
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public Subscription onChanged(ValueChangedListener<? super V> listener) {
        listeners.add(listener);
        return () -> listeners.remove(listener);
    }

    @Override
    public Subscription onWillChange(ValueWillChangeListener<? super V> listener) {
        throw new RuntimeException("Not yet implemented!");
    }
}
