package com.guigarage.observer;

import java.util.EventListener;

@FunctionalInterface
public interface ValueChangeListener<V> extends EventListener {

    void valueChanged(ValueChangeEvent<V> event);

}
