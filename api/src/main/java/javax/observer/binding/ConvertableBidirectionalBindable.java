package javax.observer.binding;

import javax.observer.Property;
import javax.observer.Subscription;
import java.util.function.Function;

public interface ConvertableBidirectionalBindable<T> extends ConvertableBindable<T>, BidirectionalBindable<T> {

    <U> Subscription bidirectionalTo(Property<U> property, Function<U, T> converter, Function<T, U> converter2);

    @Override
    default Subscription bidirectionalTo(Property<T> property) {
        return bidirectionalTo(property, v -> v, v -> v);
    }
}
