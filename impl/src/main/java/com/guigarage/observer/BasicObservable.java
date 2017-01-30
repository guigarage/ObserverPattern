package com.guigarage.observer;

import javax.observer.Observable;
import javax.observer.Subscription;
import javax.observer.ValueChangedEvent;
import javax.observer.ValueChangedListener;
import javax.observer.ValueWillChangeEvent;
import javax.observer.ValueWillChangeListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BasicObservable<V> implements Observable<V> {

    private V value;

    private final List<ValueChangedListener<? super V>> changedListeners = new CopyOnWriteArrayList<>();

    private final List<ValueWillChangeListener<? super V>> willChangelisteners = new CopyOnWriteArrayList<>();

    private final Lock valueLock = new ReentrantLock();

    protected void updateValue(final V value) {
        if (this.value != value) {
            valueLock.lock();
            try {
                this.value = value;
            } finally {
                valueLock.unlock();
                fireChangedEvent();
            }
        }
    }

    private void fireWillChangeEvent(final V newValue) {
        final ValueHolder<Boolean> veto = new ValueHolder<>(false);

        final ValueWillChangeEvent<V> event = new ValueWillChangeEvent<V>() {

            @Override
            public Observable<V> getObservable() {
                return BasicObservable.this;
            }

            @Override
            public V getNewValue() {
                return newValue;
            }

            @Override
            public void veto() {
                veto.setValue(true);
            }
        };
        willChangelisteners.forEach(l -> {
            if(!veto.getValue()) {
                l.valueWillChange(event);
            }
        });
    }

    private void fireChangedEvent() {
        final ValueChangedEvent<V> event = new ValueChangedEvent<V>() {
            @Override
            public Observable getObservable() {
                return BasicObservable.this;
            }

            @Override
            public V getValue() {
                return getValue();
            }
        };
        changedListeners.forEach(l -> l.valueChanged(event));
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public Subscription onChanged(final ValueChangedListener<? super V> listener) {
        changedListeners.add(listener);
        return () -> changedListeners.remove(listener);
    }

    @Override
    public Subscription onWillChange(final ValueWillChangeListener<? super V> listener) {
        willChangelisteners.add(listener);
        return () -> willChangelisteners.remove(listener);
    }
}
