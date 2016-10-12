package com.guigarage.observer.collection;

import java.util.List;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface ObservableList<E> extends List<E>, ObservableCollection<E, ObservableList<E>, ListChange<E>> {

}
