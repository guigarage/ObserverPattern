package javax.observer.tck;

import javax.observer.Observable;
import javax.observer.Subscription;
import javax.observer.tck.util.ValueHolder;
import java.util.Objects;
import java.util.function.Consumer;

public class CheckBooleanObservable {

    public static void checkBooleanObservableBasicMethods(final Observable<Boolean> observable) {
        Objects.requireNonNull(observable);
        observable.getValue();
        observable.value();
        observable.value().orElse(true);

        Subscription willChangeSubscription = observable.onWillChange(e -> new RuntimeException("FAIL"));
        willChangeSubscription.unsubscribe();

        Subscription changedSubscription = observable.onChanged(e -> new RuntimeException("FAIL"));
        changedSubscription.unsubscribe();
    }

    public static void checkBooleanObservableChangeFromTrueToFalse(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.TRUE);
        observable.value().orElseThrow(() -> new BadImplementationException("FAIL"));

        final ValueHolder<Boolean> valeWillChangeHolder = new ValueHolder<>(false);
        final Subscription willChangeSubscription = observable.onWillChange(e -> valeWillChangeHolder.setValue(true));

        final ValueHolder<Boolean> valeChangedHolder = new ValueHolder<>(false);
        final Subscription changedSubscription = observable.onChanged(e -> valeChangedHolder.setValue(true));

        if (observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if (observable.value().get() != true) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(Boolean.FALSE);
        if (observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        if (observable.value().get() != false) {
            throw new BadImplementationException("FAIL");
        }
        if (valeWillChangeHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if (valeChangedHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        willChangeSubscription.unsubscribe();
        changedSubscription.unsubscribe();
        if (observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        if (observable.value().get() != false) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableChangeFromFalseToTrue(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.FALSE);

        final ValueHolder<Boolean> valeWillChangeHolder = new ValueHolder<>(false);
        final Subscription willChangeSubscription = observable.onWillChange(e -> valeWillChangeHolder.setValue(true));

        final ValueHolder<Boolean> valueChangedHolder = new ValueHolder<>(false);
        final Subscription changedSubscription = observable.onChanged(e -> valueChangedHolder.setValue(true));

        if (observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(Boolean.TRUE);
        if (observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if (valeWillChangeHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if (valueChangedHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        willChangeSubscription.unsubscribe();
        changedSubscription.unsubscribe();
        if (observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableChangeFromTrueToNull(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.TRUE);

        final ValueHolder<Boolean> valeWillChangeHolder = new ValueHolder<>(false);
        final Subscription willChangeSubscription = observable.onWillChange(e -> valeWillChangeHolder.setValue(true));

        final ValueHolder<Boolean> valueChangedHolder = new ValueHolder<>(false);
        final Subscription changedSubscription = observable.onChanged(e -> valueChangedHolder.setValue(true));

        if (observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(null);
        if (observable.getValue() != null) {
            throw new BadImplementationException("FAIL");
        }
        if (valeWillChangeHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if (valueChangedHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        willChangeSubscription.unsubscribe();
        changedSubscription.unsubscribe();
        if (observable.getValue() != null) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableChangeFromFalseToNull(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.FALSE);

        final ValueHolder<Boolean> valeWillChangeHolder = new ValueHolder<>(false);
        final Subscription willChangeSubscription = observable.onWillChange(e -> valeWillChangeHolder.setValue(true));

        final ValueHolder<Boolean> valueChangedHolder = new ValueHolder<>(false);
        final Subscription changedSubscription = observable.onChanged(e -> valueChangedHolder.setValue(true));

        if (observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(null);
        if (observable.getValue() != null) {
            throw new BadImplementationException("FAIL");
        }
        if (valeWillChangeHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if (valueChangedHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        willChangeSubscription.unsubscribe();
        changedSubscription.unsubscribe();
        if (observable.getValue() != null) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableFireNoChangeForSameValue(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.TRUE);

        final ValueHolder<Boolean> valeWillChangeHolder = new ValueHolder<>(false);
        final Subscription willChangeSubscription = observable.onWillChange(e -> valeWillChangeHolder.setValue(true));

        final ValueHolder<Boolean> valueChangedHolder = new ValueHolder<>(false);
        final Subscription changedSubscription = observable.onChanged(e -> valueChangedHolder.setValue(true));

        if (observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(Boolean.TRUE);
        if (observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if (valeWillChangeHolder.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        if (valueChangedHolder.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        willChangeSubscription.unsubscribe();
        changedSubscription.unsubscribe();
        if (observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableNoValueChangedAfterUnsubscribe(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.TRUE);

        final ValueHolder<Boolean> valeWillChangeHolder = new ValueHolder<>(false);
        final Subscription willChangeSubscription = observable.onWillChange(e -> valeWillChangeHolder.setValue(true));

        final ValueHolder<Boolean> valueChangedHolder = new ValueHolder<>(false);
        final Subscription changedSubscription = observable.onChanged(e -> valueChangedHolder.setValue(true));

        if (observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(Boolean.FALSE);
        if (observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        if (valeWillChangeHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if (valueChangedHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        willChangeSubscription.unsubscribe();
        changedSubscription.unsubscribe();
        valeWillChangeHolder.setValue(false);
        valueChangedHolder.setValue(false);
        changeObservableValue.accept(Boolean.TRUE);
        if (observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if (valeWillChangeHolder.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        if (valueChangedHolder.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkChangeInWillChangeListenerEndsInError(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        final ValueHolder<IllegalStateException> exceptionHolder = new ValueHolder<>();


        observable.onWillChange(e -> {
            try {
                changeObservableValue.accept(Boolean.FALSE);
            } catch (IllegalStateException ex) {
                exceptionHolder.setValue(ex);
            }
        });
        changeObservableValue.accept(Boolean.TRUE);

        if(exceptionHolder.getValue() == null) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkVetoWillNotResultInChange(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        final ValueHolder<BadImplementationException> exceptionHolder = new ValueHolder<>();


        observable.onWillChange(e -> e.veto());

        observable.onChanged(e -> exceptionHolder.setValue(new BadImplementationException("FAIL")));

        changeObservableValue.accept(Boolean.TRUE);

        if(exceptionHolder.getValue() != null) {
            throw exceptionHolder.getValue();
        }
    }

}
