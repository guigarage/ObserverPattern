package javax.observer.binding;

import javax.observer.Property;
import javax.observer.Subscription;
import java.util.function.Function;

/**
 * Adds the support of different type bindings to the {@link BidirectionalBindable} interface. This interface
 * offers bindings between different value types by defining {@link Function} based converters.
 * @param <T> value type
 */
public interface ConvertableBidirectionalBindable<T, I extends ConvertableBidirectionalBindable<T, I>> extends ConvertableBindable<T, I>, BidirectionalBindable<T, I> {

    /**
      *By defining a converter a bidirectional binding to an {@link Property} with a different value
     * type can be defined. For more information about binding see {@link BidirectionalBindable#bidirectionalTo(Property)}
     * @param property
     * @param converter the converter that will be used to convert any new value of the {@link Property}
     *                  to the correct value type
     * @param converter2 the converter that will be used to convert any new value to the value type of the {@link Property}
     * @param <U> value type of the {@link Property} to that this instance will be bound.
     * @return a {@link Subscription} to unbind the binding that is created by calling this method
     */
    <U> Subscription bidirectionalTo(Property<U> property, Function<U, T> converter, Function<T, U> converter2);

    @Override
    default Subscription bidirectionalTo(Property<T> property) {
        return bidirectionalTo(property, v -> v, v -> v);
    }
}
