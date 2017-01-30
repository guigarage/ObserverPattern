package javax.observer.binding;

import javax.observer.Observable;
import javax.observer.Subscription;

public interface Bindable<T> {

    Subscription to(Observable<T> observable);

}
