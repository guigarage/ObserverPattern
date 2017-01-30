package javax.observer.binding;

import javax.observer.Observable;
import javax.observer.Subscription;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ConvertableBindable<T> extends Bindable<T> {

    <U> Subscription to(Observable<U> observable, Function<U, T> converter);

    @Override
    default Subscription to(Observable<T> observable) {
        return to(observable, v -> v);
    }

    ConvertableBindable<T> withErrorHandler(Consumer<Throwable> handler);
}
