package javax.observer.collection;

/**
 * Represents a list change. A list change consists of an element list
 * containing the changed elements and an index array which contains the indices
 * of the changed elements.
 *
 * @author Hendrik Ebbers (TODO email missing)
 * @author Michael Hoffer (info@michaelhoffer.de)
 */
public interface ListChange<E> extends CollectionChange<E, ObservableList<E>> {

    /**
     * Returns the indices of the elements that are affected by this change. Returning {@code null} is not supported.
     * Implementations must return an empty array instead to comply with this API.
     *
     * @return the indices of the elements that are affected by this change
     */
    int[] indices();

    /**
     * Returns an empty collection change.
     *
     * @param <sE> element type
     * @param <sC> collection type
     * @return empty collection change
     */
    static <sE, sC extends ObservableCollection<sE, sC, ?>> CollectionChange<sE, sC> empty() {
        // TODO return empty change
        return null;
    }

}
