package javax.observer.collection;


import java.util.List;

/**
 * Represents a collection change. A change consists of an element list
 * containing the changed elements.
 *
 * @author Hendrik Ebbers TODO add email
 * @author Michael Hoffer (info@michaelhoffer.de)
 */
public interface CollectionChange<E, C extends ObservableCollection<E, C, ?>> {

    /**
     * Returns the elements affected by this change. Returning {@code null} is not supported.
     * Implementations must return an empty collection instead to comply with this API.
     *
     * @return the elements affected by this change
     */
    List<E> elements();

    /**
     * Returns an empty collection change.
     * @param <sE> element type
     * @param <sC> collection type
     *
     * @return empty collection change
     */
    static <sE,sC extends ObservableCollection<sE, sC, ?>> javax.observer.collection.CollectionChange<sE,sC> empty() {
        // TODO return empty change
        return null;
    }

    /**
     * Indicates whether this object contains list changes.
     *
     * @return {@code true} if this object contains changes; {@code false}
     * otherwise
     */
    default boolean hasChanges() {
        return !elements().isEmpty();
    }
}
