package javax.observer.binding;


import javax.observer.Property;
import javax.observer.Subscription;

/**
 * Provides support for bidirectional bindings.
 * @param <T>
 */
public interface BidirectionalBindable<T> extends Bindable<T> {

    Subscription bidirectionalTo(Property<T> property);

}
