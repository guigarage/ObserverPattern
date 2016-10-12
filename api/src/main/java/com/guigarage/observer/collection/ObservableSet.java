package com.guigarage.observer.collection;

import java.util.Set;

/**
 * Created by hendrikebbers on 12.10.16.
 */
public interface ObservableSet<E> extends Set<E>, ObservableCollection<E, ObservableSet<E>, SetChange<E>> {

}
