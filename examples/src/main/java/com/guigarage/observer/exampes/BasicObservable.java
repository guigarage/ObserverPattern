package com.guigarage.observer.exampes;

import com.guigarage.observer.Observable;
import com.guigarage.observer.Subscription;
import com.guigarage.observer.ValueChangeEvent;
import com.guigarage.observer.ValueChangeListener;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BasicObservable<V> implements Observable<V> {

    private V value;

    private final List<ValueChangeListener<? super V>> listeners = new CopyOnWriteArrayList<>();

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
        final ValueChangeEvent<V> event = new ValueChangeEvent<V>() {
            @Override
            public Observable getObservable() {
                return BasicObservable.this;
            }

            @Override
            public V getOldValue() {
                return oldValue;
            }

            @Override
            public V getNewValue() {
                return newValue;
            }
        };
        listeners.forEach(l -> l.valueChanged(event));
    }

    @Override
    public V get() {
        return value;
    }

    @Override
    public Subscription onChanged(ValueChangeListener<? super V> listener) {
        listeners.add(listener);
        return () -> listeners.remove(listener);
    }
}
