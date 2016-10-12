package com.guigarage.observer.collection;

import com.guigarage.observer.Subscription;

import java.util.Collection;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface ObservableCollection<E, C extends ObservableCollection<E, C, ?>, L extends CollectionChange<E, C>> extends Collection<E> {

    Subscription onChanged(CollectionChangeListener<? super E, C, L> listener);

}
