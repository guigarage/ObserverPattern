package javax.observer.collection;

import javax.observer.Subscription;
import java.util.Collection;

/**
 * TODO
 * @author Hendrik Ebbers (TODO add email)
 */
public interface ObservableCollection<E, C extends javax.observer.collection.ObservableCollection<E, C, ?>, L extends CollectionChange<E, C>> extends Collection<E> {

    Subscription onChanged(CollectionChangeListener<? super E, C, L> listener);

}
