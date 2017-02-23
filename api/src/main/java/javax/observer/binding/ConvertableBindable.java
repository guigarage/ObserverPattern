package javax.observer.binding;

import javax.observer.Observable;
import javax.observer.Subscription;
import java.util.function.Function;

/**
 * Adds the support of different type bindings to the {@link Bindable} interface. This interface
 * offers bindings between different value types by defining {@link Function} based converters.
 * @param <T> value type
 */
public interface ConvertableBindable<T, I extends ConvertableBindable<T, I>> extends Bindable<T, I> {

    /**
     * By defining a converter a unidirectional binding to an {@link Observable} with a different value
     * type can be defined. For more information about binding see {@link Bindable#to(Observable)}
     * @param observable the {@link Observable} to that this instance will be bound.
     * @param converter the converter that will be used to convert any new value of the {@link Observable}
     *                  to the correct value type
     * @param <U> value type of the {@link Observable} to that this instance will be bound.
     * @return a {@link Subscription} to unbind the binding that is created by calling this method
     */
    <U> Subscription to(Observable<U> observable, Function<U, T> converter);

    @Override
    default Subscription to(Observable<T> observable) {
        return to(observable, v -> v);
    }

}
