package javax.observer.collection;

/**
 * Represents a collection change event. An event contains information about all changes made by
 * the action that fired the event.
 * <p>
 * Events contain information about added and removed elements. Additionally,
 * events contain information whether the changes were caused by a set
 * opperation (removed elements are replaced by added elements).
 *
 * @author Hendrik Ebbers (TODO add email here)
 * @author Michael Hoffer (info@michaelhoffer)
 */
public interface CollectionChangeEvent<E, C extends ObservableCollection<E, C, ?>, V extends CollectionChange<E, C>> {

    /**
     * Returns the source collection, e.g., the collection that fired the change event
     *
     * @return the source collection
     */
    C source();

    /**
     * Returns the change that contains all elements that were added during this
     * event.
     *
     * @return the change that contains all elements that were added during this
     * event
     */
    CollectionChange<E, C> added();

    /**
     * Returns the change that contains all elements that were removed during
     * this event.
     *
     * @return the change that contains all elements that were removed during
     * this event
     */
    CollectionChange<E, C> removed();


    /**
     * Indicates whether elements were added during this event.
     *
     * @return {@code true} if elements were added during this event;
     * {@code false} otherwise
     */
    boolean wasAdded();

    /**
     * Indicates whether elements were removed during this event.
     *
     * @return {@code true} if elements were removed during this event;
     * {@code false} otherwise
     */
    boolean wasRemoved();

    /**
     * Indicates whether elements were set, e.g., replaced during this event.
     *
     * @return {@code true} if elements were set during this event;
     * {@code false} otherwise
     */
    boolean wasSet();

    // TODO do we need wasPermutated() like JavaFX does?

}
