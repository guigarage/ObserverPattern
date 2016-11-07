package javax.observer.tck;

import javax.observer.Observable;
import javax.observer.Subscription;
import javax.observer.ValueChangeEvent;
import javax.observer.ValueChangeListener;
import java.util.Objects;

public class CheckBooleanObservableContent {

    public static void checkBooleanObservableBasicMethods(final Observable<Boolean> observable) {
        Objects.requireNonNull(observable);
        observable.get();
        observable.value().orElse(true);
        final Subscription subscription = observable.onChanged(new ValueChangeListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Boolean> event) {
                throw new RuntimeException("FAIL");
            }
        });
        subscription.unsubscribe();
    }

    public static void checkBooleanObservableChangeFromTrueToFalse(final Observable<Boolean> observable, final Runnable changeValue) {
        Objects.requireNonNull(observable);
        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangeListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.get() != true) {
            throw new RuntimeException("FAIL");
        }
        changeValue.run();
        if(observable.get() != false) {
            throw new RuntimeException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new RuntimeException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.get() != false) {
            throw new RuntimeException("FAIL");
        }
    }

    public static void checkBooleanObservableChangeFromFalseToTrue(final Observable<Boolean> observable, final Runnable changeValue) {
        Objects.requireNonNull(observable);
        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangeListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.get() != false) {
            throw new RuntimeException("FAIL");
        }
        changeValue.run();
        if(observable.get() != true) {
            throw new RuntimeException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new RuntimeException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.get() != true) {
            throw new RuntimeException("FAIL");
        }
    }

    public static void checkBooleanObservableChangeFromTrueToNull(final Observable<Boolean> observable, final Runnable changeValue) {
        Objects.requireNonNull(observable);
        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangeListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.get() != true) {
            throw new RuntimeException("FAIL");
        }
        changeValue.run();
        if(observable.get() != null) {
            throw new RuntimeException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new RuntimeException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.get() != null) {
            throw new RuntimeException("FAIL");
        }
    }

    public static void checkBooleanObservableChangeFromFalseToNull(final Observable<Boolean> observable, final Runnable changeValue) {
        Objects.requireNonNull(observable);
        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangeListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.get() != false) {
            throw new RuntimeException("FAIL");
        }
        changeValue.run();
        if(observable.get() != null) {
            throw new RuntimeException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new RuntimeException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.get() != null) {
            throw new RuntimeException("FAIL");
        }
    }

    public static void checkBooleanObservableFireNoChangeForSameValue(final Observable<Boolean> observable, final Runnable changeValue) {
        Objects.requireNonNull(observable);
        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangeListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.get() != false) {
            throw new RuntimeException("FAIL");
        }
        changeValue.run();
        if(observable.get() != false) {
            throw new RuntimeException("FAIL");
        }
        if(valueHolder.getValue() != false) {
            throw new RuntimeException("FAIL");
        }
        subscription.unsubscribe();
        if(observable.get() != false) {
            throw new RuntimeException("FAIL");
        }
    }

    public static void checkBooleanObservableNoValueChangedAfterUnsubscribe(final Observable<Boolean> observable, final Runnable changeValue, final Runnable changeValue2) {
        Objects.requireNonNull(observable);
        final ValueHolder<Boolean> valueHolder = new ValueHolder<>(false);
        final Subscription subscription = observable.onChanged(new ValueChangeListener<Boolean>() {
            @Override
            public void valueChanged(ValueChangeEvent<? extends Boolean> event) {
                valueHolder.setValue(true);
            }
        });
        if(observable.get() != false) {
            throw new RuntimeException("FAIL");
        }
        changeValue.run();
        if(observable.get() != true) {
            throw new RuntimeException("FAIL");
        }
        if(valueHolder.getValue() != true) {
            throw new RuntimeException("FAIL");
        }
        valueHolder.setValue(false);
        changeValue2.run();
        if(observable.get() != false) {
            throw new RuntimeException("FAIL");
        }
        if(valueHolder.getValue() != false) {
            throw new RuntimeException("FAIL");
        }
    }

}
