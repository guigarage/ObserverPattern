package javax.observer.collection;

import java.util.EventListener;

/**
 * TODO
 * @author Hendrik Ebbers
 */
@FunctionalInterface
public interface CollectionChangeListener<E, C extends ObservableCollection<E, C, ?>, V extends CollectionChange<E, C>> extends EventListener {

    void onChanged(CollectionChangeEvent<E, C, V> e);
}

