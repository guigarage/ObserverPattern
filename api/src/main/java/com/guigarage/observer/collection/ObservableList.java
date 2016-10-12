package com.guigarage.observer.collection;

import java.util.List;

/**
 * Created by hendrikebbers on 12.10.16.
 */
public interface ObservableList<E> extends List<E>, ObservableCollection<E, ObservableList<E>, ListChange<E>> {

}
