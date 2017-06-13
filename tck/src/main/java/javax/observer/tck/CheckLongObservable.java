package javax.observer.tck;

import javax.observer.Observable;
import javax.observer.Subscription;
import java.util.Objects;

/**
 * 
 * @author Thomas Zimmermann (https://github.com/zimmi)
 */
public class CheckLongObservable
{

    public static void checkLongObservableBasicMethods(final Observable<Long> observable)
    {
        Objects.requireNonNull(observable);
        observable.getValue();
        observable.value();
        observable.value().orElse(0L);

        final Subscription willChangeSubscription = observable.onWillChange(e -> new RuntimeException("FAIL"));
        willChangeSubscription.unsubscribe();

        final Subscription changedSubscription = observable.onChanged(e -> new RuntimeException("FAIL"));
        changedSubscription.unsubscribe();
    }

}
