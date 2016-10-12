package com.guigarage.observer.collection;

/**
 * Created by hendrikebbers on 12.10.16.
 */
public interface CollectionChange<E, C extends ObservableCollection<E, C, ?>> {

    boolean wasAdded();

    boolean wasRemoved();

    C getCollection();
}
