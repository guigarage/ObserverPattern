package javax.observer.collection;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface CollectionChangeEvent<E, C extends ObservableCollection<E, C, ?>, V extends CollectionChange<E, C>> {

    C getCollection();

    Stream<V> changes();

    default List<V> getChanges() {
        return Collections.unmodifiableList(changes().collect(Collectors.toList()));
    }
}
