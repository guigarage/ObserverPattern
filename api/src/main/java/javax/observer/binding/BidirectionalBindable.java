package javax.observer.binding;


import javax.observer.Property;
import javax.observer.Subscription;

/**
 * Provides support for bidirectional bindings.
 *
 * TODO: should this extend {@link Property}???
 * @param <T> value type
 */
public interface BidirectionalBindable<T, I extends BidirectionalBindable<T, I>> extends Bindable<T, I> {

    /**
     * Creates an bidirectional binding by binding this Bindable to the given {@link Property}.
     * By doing so any change of the value of the {@link Property} will be propagated to this object. And any change of
     * this object will be propagated to the given {@link Property}.
     * @param property the property
     * @return  a {@link Subscription} to unbind the binding that is created by calling this method
     */
    Subscription bidirectionalTo(Property<T> property);

}
