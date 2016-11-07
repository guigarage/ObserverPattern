package javax.observer;

import java.util.EventListener;

/**
 * TODO
 * @author Hendrik Ebbers
 */
@FunctionalInterface
public interface ValueChangeListener<V> extends EventListener {

    void valueChanged(ValueChangeEvent<? extends V> event);

}
