package javax.observer.collection;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface CollectionChange<E, C extends ObservableCollection<E, C, ?>> {

    boolean wasAdded();

    boolean wasRemoved();

    C getCollection();
}
