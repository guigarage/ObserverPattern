package javax.observer.tck;

import javax.observer.Observable;
import javax.observer.Subscription;
import javax.observer.ValueChangedEvent;
import javax.observer.ValueChangedListener;
import javax.observer.tck.util.ValueHolder;
import java.util.Objects;
import java.util.function.Consumer;

public class CheckBooleanObservable {

    public static void checkBooleanObservableBasicMethods(final Observable<Boolean> observable) {
        Objects.requireNonNull(observable);
        observable.getValue();
        observable.value();
        observable.value().orElse(true);
        final Subscription subscription = observable.onChanged(new ValueChangedListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangedEvent<? extends Boolean> event) {
                throw new RuntimeException("FAIL");
            }
        });
        subscription.unsubscribe();
    }

    public static void checkBooleanObservableChangeFromTrueToFalse(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.TRUE);
        observable.value().orElseThrow(() -> new BadImplementationException("FAIL"));
        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangedListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangedEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if(observable.value().get() != true) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(Boolean.FALSE);
        if(observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        if(observable.value().get() != false) {
            throw new BadImplementationException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        if(observable.value().get() != false) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableChangeFromFalseToTrue(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.FALSE);
        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangedListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangedEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(Boolean.TRUE);
        if(observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableChangeFromTrueToNull(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.TRUE);

        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangedListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangedEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(null);
        if(observable.getValue() != null) {
            throw new BadImplementationException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.getValue() != null) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableChangeFromFalseToNull(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.FALSE);

        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangedListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangedEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(null);
        if(observable.getValue() != null) {
            throw new BadImplementationException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.getValue() != null) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableFireNoChangeForSameValue(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.TRUE);

        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangedListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangedEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(Boolean.TRUE);
        if(observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if(valueHolder.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
    }

    public static void checkBooleanObservableNoValueChangedAfterUnsubscribe(final Observable<Boolean> observable, final Consumer<Boolean> changeObservableValue) throws BadImplementationException {
        Objects.requireNonNull(observable);
        Objects.requireNonNull(changeObservableValue);

        changeObservableValue.accept(Boolean.TRUE);

        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangedListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangedEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        changeObservableValue.accept(Boolean.FALSE);
        if(observable.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        subscription.unsubscribe();
        valueHolder.setValue(false);
        changeObservableValue.accept(Boolean.TRUE);
        if(observable.getValue() != true) {
            throw new BadImplementationException("FAIL");
        }
        if(valueHolder.getValue() != false) {
            throw new BadImplementationException("FAIL");
        }
    }

}
