package com.guigarage.observer.collection;

import java.util.List;

public interface ListChange<E> extends CollectionChange<E, ObservableList<E>> {

    int getFrom();

    int getTo();

    List<E> getRemovedElements();

    boolean wasReplaced();
}
