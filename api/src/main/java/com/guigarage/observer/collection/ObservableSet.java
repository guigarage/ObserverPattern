package com.guigarage.observer.collection;

import java.util.Set;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface ObservableSet<E> extends Set<E>, ObservableCollection<E, ObservableSet<E>, SetChange<E>> {

}
