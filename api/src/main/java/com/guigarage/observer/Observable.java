package com.guigarage.observer;

public interface Observable<V> {

    V get();

    Subscription onChanged(ValueChangeListener<? super V> listener);
}
