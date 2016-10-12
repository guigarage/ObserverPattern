package com.guigarage.observer.collection;

import java.util.List;

/**
 * Created by hendrikebbers on 12.10.16.
 */
public interface CollectionChangeEvent<E, C extends ObservableCollection<E, C, ?>, V extends CollectionChange<E, C>> {

    C getCollection();

    List<V> getChanges();
}
