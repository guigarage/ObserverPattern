package javax.observer;

import java.util.EventListener;

/**
 * TODO
 * @author Hendrik Ebbers
 */
@FunctionalInterface
public interface ValueChangedListener<V> extends EventListener {

    void valueChanged(ValueChangedEvent<? extends V> event);

}
