package com.guigarage.observer.collection;

import java.util.List;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface CollectionChangeEvent<E, C extends ObservableCollection<E, C, ?>, V extends CollectionChange<E, C>> {

    C getCollection();

    List<V> getChanges();
}
