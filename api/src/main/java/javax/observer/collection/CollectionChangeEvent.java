/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.observer.collection;

import java.util.Collection;

/**
 * 
 * @param <T> element type of the collection
 * @param <OC> observed collection type
 * @param <CC> collection change type
 * 
 * @author Michael Hoffer (info@michaelhoffer.de)
 */
public interface CollectionChangeEvent<T, OC extends Collection<T>, CC extends CollectionChange<T>> {
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

    /**
     * Returns the change that contains all elements that were added during this
     * event.
     *
     * @return the change that contains all elements that were added during this
     * event
     */
    CC added();

    /**
     * Returns the change that contains all elements that were removed during
     * this event.
     *
     * @return the change that contains all elements that were removed during
     * this event
     */
    CC removed();

    /**
     * Returns the source collection, e.g., the collection that fired the change event
     *
     * @return the source list
     */
    OC source();

    
}
