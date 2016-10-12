package com.guigarage.observer.collection;

import com.guigarage.observer.Subscription;

import java.util.Collection;

/**
 * Created by hendrikebbers on 12.10.16.
 */
public interface ObservableCollection<E, C extends ObservableCollection<E, C, ?>, L extends CollectionChange<E, C>> extends Collection<E> {

    Subscription onChanged(CollectionChangeListener<? super E, C, L> listener);

}
