package javax.observer.collection;


import java.util.List;

/**
 * 
 * @author Michael Hoffer <info@michaelhoffer.de>
 * @param <T> element type os the collection
 */
public interface CollectionChange<T> {
    List<T> elements();
}
