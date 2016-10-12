package com.guigarage.observer.collection;

/**
 * Created by hendrikebbers on 12.10.16.
 */
public interface SetChange<E> extends CollectionChange<E, ObservableSet<E>> {

    E getAddedElement();

    E getRemovedElement();
}
