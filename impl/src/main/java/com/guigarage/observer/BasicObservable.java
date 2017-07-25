package com.guigarage.observer;

import javax.observer.Observable;
import javax.observer.Subscription;
import javax.observer.ValueChangedEvent;
import javax.observer.ValueChangedListener;
import javax.observer.ValueWillChangeListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BasicObservable<V> implements Observable<V> {

    private V value;

    private final List<ValueChangedListener<? super V>> changedListeners = new CopyOnWriteArrayList<>();

    private final List<ValueWillChangeListener<? super V>> willChangeListeners = new CopyOnWriteArrayList<>();

    private boolean inWillChangeEvent = false;

    protected void updateValue(final V value) {
        if(inWillChangeEvent) {
            throw new IllegalStateException("Value can not change while willChangeEvents are handled!");
        }
        if (this.value != value) {
            boolean canChange = fireWillChangeEvent(value);
            if(canChange) {
                try {
                    this.value = value;
                } finally {
                    fireChangedEvent(this.value);
                }
            }
        }
    }

    private boolean fireWillChangeEvent(final V newValue) {
        inWillChangeEvent = true;
        try {
            final ValueWillChangeEventImpl<V> event = new ValueWillChangeEventImpl<>(this, newValue);
            Iterator<ValueWillChangeListener<? super V>> listenerIterator = willChangeListeners.iterator();
            while (listenerIterator.hasNext() && !event.isVetoCalled()) {
                listenerIterator.next().valueWillChange(event);
            }
            return !event.isVetoCalled();
        } finally {
            inWillChangeEvent = false;
        }
    }

    private void fireChangedEvent(final V newValue) {
        final ValueChangedEvent<V> event = new ValueChangedEvent<V>() {
            @Override
            public Observable<V> getObservable() {
                return BasicObservable.this;
            }

            @Override
            public V getValue() {
                return newValue;
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
        willChangeListeners.add(listener);
        return () -> willChangeListeners.remove(listener);
    }
}
