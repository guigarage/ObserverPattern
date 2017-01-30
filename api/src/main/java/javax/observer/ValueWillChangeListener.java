package javax.observer;

import java.util.EventListener;

@FunctionalInterface
public interface ValueWillChangeListener<V> extends EventListener {

    void valueWillChange(ValueWillChangeEvent<? extends V> event);

}
