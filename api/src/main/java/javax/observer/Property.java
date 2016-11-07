package javax.observer;

/**
 * TODO
 * @author Hendrik Ebbers
 */
public interface Property<V> extends Observable<V> {

    void set(V value);
}
