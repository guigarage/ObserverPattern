package com.guigarage.binding;

import javax.observer.Observable;
import javax.observer.Property;
import javax.observer.Subscription;
import javax.observer.binding.ConvertableBidirectionalBindable;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Class that provides basic functionallity for bindings wothout handling any thread issues, etc. This is not thread safe
 * and should only be used for Bindings that will be handled on the same Thread.
 */
public class Binding {

    private static class InternalConvertableBidirectionalBindable<T> implements ConvertableBidirectionalBindable<T, InternalConvertableBidirectionalBindable<T>> {

        private final Property<T> property;

        private Consumer<Throwable> errorHandler = e -> e.printStackTrace();

        private boolean bindingCalled = false;

        public InternalConvertableBidirectionalBindable(Property<T> property) {
            this.property = property;
        }

        @Override
        public <U> Subscription bidirectionalTo(Property<U> toProperty, Function<U, T> converter, Function<T, U> converter2) {
            Subscription subscription1 = to(toProperty, converter);
            Subscription subscription2 = bind(property, toProperty, converter2);
            return () -> {
                subscription1.unsubscribe();
                subscription2.unsubscribe();
            };
        }

        @Override
        public <U> Subscription to(Observable<U> observable, Function<U, T> converter) {
            return bind(observable, property, converter);
        }

        private <U, V> Subscription bind(Observable<U> observable, Property<V> property, Function<U, V> converter) {
            return observable.onChanged(e -> {
                if (!bindingCalled) {
                    bindingCalled = true;
                    try {
                        property.setValue(converter.apply(e.getValue()));
                    } catch (Exception ex) {
                        errorHandler.accept(ex);
                    } finally {
                        bindingCalled = false;
                    }
                }
            });
        }

        @Override
        public InternalConvertableBidirectionalBindable<T> withErrorHandler(Consumer<Throwable> handler) {
            this.errorHandler = errorHandler;
            return this;
        }
    }

    public static <T> ConvertableBidirectionalBindable<T, ?> bind(Property<T> property) {
        return new InternalConvertableBidirectionalBindable<T>(property);
    }
}
