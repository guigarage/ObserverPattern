package com.guigarage.observer.collection;

import java.util.EventListener;

/**
 * Created by hendrikebbers on 12.10.16.
 */
@FunctionalInterface
public interface CollectionChangeListener<E, C extends ObservableCollection<E, C, ?>, V extends CollectionChange<E, C>> extends EventListener {

    void onChanged(CollectionChangeEvent<E, C, V> e);
}

