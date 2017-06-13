package javax.observer.tck;

import javax.observer.Observable;
import javax.observer.Subscription;
import java.util.Objects;

public class CheckLongObservable
{

    public static void checkLongObservableBasicMethods(final Observable<Long> observable)
    {
        Objects.requireNonNull(observable);
        observable.getValue();
        observable.value();
        observable.value().orElse(0L);

        Subscription willChangeSubscription = observable.onWillChange(e -> new RuntimeException("FAIL"));
        willChangeSubscription.unsubscribe();

        Subscription changedSubscription = observable.onChanged(e -> new RuntimeException("FAIL"));
        changedSubscription.unsubscribe();
    }

}
