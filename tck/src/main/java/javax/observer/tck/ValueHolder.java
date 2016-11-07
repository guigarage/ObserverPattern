package javax.observer.tck;

/**
 * Created by hendrikebbers on 31.10.16.
 */
public class ValueHolder<T> {

    T value;

    public ValueHolder() {
    }

    public ValueHolder(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
