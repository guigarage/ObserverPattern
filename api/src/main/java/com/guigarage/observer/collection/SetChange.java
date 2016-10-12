package com.guigarage.observer.collection;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface SetChange<E> extends CollectionChange<E, ObservableSet<E>> {

    E getAddedElement();

    E getRemovedElement();
}
