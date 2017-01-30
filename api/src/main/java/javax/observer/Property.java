package javax.observer;

/**
 * Adds mutation support to the {@link Observable} interface. Implementations of this interface
 * provides full support for setting and getting the internal value.
 * @author Hendrik Ebbers
 */
public interface Property<V> extends Observable<V> {

    /**
     * Replaces the internal value with the given new value. This should end in calling the
     * {@link ValueChangeListener#valueChanged(ValueChangeEvent)} method on all registered
     * listeners.
     * @param value the new value
     */
    void setValue(V value);
}
