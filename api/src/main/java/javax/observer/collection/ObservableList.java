package javax.observer.collection;

import java.util.List;

/**
 * TODO
 * @author Hendrik Ebbers (TODO add email)
 */
public interface ObservableList<E> extends List<E>, CollectionObservable<E, ObservableList<E>, ListChange<E>> {

}
