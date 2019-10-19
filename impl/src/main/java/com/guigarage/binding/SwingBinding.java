package com.guigarage.binding;

import javax.observer.Observable;
import javax.observer.Property;
import javax.observer.Subscription;
import javax.observer.binding.ConvertableBidirectionalBindable;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Provides Bindings for Swing. Swing attributes will automatically be handled in the EDT and properties will
 * automatically be handled in a background thread.
 */
public class SwingBinding {

    private static Executor backgroundExecutor = Executors.newSingleThreadExecutor();

    private static class InternalConvertableBidirectionalBindable<T> implements ConvertableBidirectionalBindable<T, InternalConvertableBidirectionalBindable<T>> {

        private AtomicBoolean bindingCalled = new AtomicBoolean(false);

        private Consumer<Throwable> errorHandler = e -> e.printStackTrace();

        private Lock bindingLock = new ReentrantLock();

        private final Component component;

        private final String attribute;

        public InternalConvertableBidirectionalBindable(Component component, String attribute) {
            this.component = component;
            this.attribute = attribute;
        }

        private <U> U callLocked(Supplier<U> supplier) {
            bindingLock.lock();
            try {
                return supplier.get();
            } finally {
                bindingLock.unlock();
            }
        }

        private void callLocked(Runnable runnable) {
            callLocked(() -> {
                runnable.run();
                return null;
            });
        }

        @Override
        public <U> Subscription bidirectionalTo(Property<U> property, Function<U, T> converter, Function<T, U> converter2) {
            try {
                final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(attribute, component.getClass());
                final PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(component);
                Subscription subscription = bind(property, converter, propertyEditor);

                PropertyChangeListener listener = e -> {
                    if (!bindingCalled.get()) {
                        bindingCalled.set(true);
                        try {
                            backgroundExecutor.execute(() -> {
                                callLocked(() -> {
                                    try {
                                        property.setValue(converter2.apply((T) e.getNewValue()));
                                    } catch (Exception ex) {
                                        SwingUtilities.invokeLater(() -> {
                                            errorHandler.accept(ex);
                                        });
                                    }
                                });
                            });
                        } catch (Exception e1) {
                            errorHandler.accept(e1);
                        } finally {
                            bindingCalled.set(false);
                        }
                    }
                };

                component.addPropertyChangeListener(listener);

                return () -> {
                    component.removePropertyChangeListener(listener);
                    subscription.unsubscribe();
                };
            } catch (Exception e1) {
                throw new RuntimeException("Can not bind!", e1);
            }
        }

        @Override
        public <U> Subscription to(Observable<U> observable, Function<U, T> converter) {
            try {
                final PropertyDescriptor propertyDescriptor = new PropertyDescriptor(attribute, component.getClass());
                final PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(component);
                return bind(observable, converter, propertyEditor);
            } catch (Exception e1) {
                throw new RuntimeException("Can not bind!", e1);
            }
        }

        private <U> Subscription bind(Observable<U> observable, Function<U, T> converter, PropertyEditor propertyEditor) {
            return observable.onChanged(e -> {
                callLocked(() -> {
                    try {
                        SwingUtilities.invokeAndWait(() -> {
                            if (!bindingCalled.get()) {
                                bindingCalled.set(true);
                                try {
                                    propertyEditor.setValue(converter.apply(e.getValue()));
                                } catch (Exception e1) {
                                    errorHandler.accept(e1);
                                } finally {
                                    bindingCalled.set(false);
                                }
                            }
                        });
                    } catch (Exception e1) {
                        SwingUtilities.invokeLater(() -> {
                            errorHandler.accept(e1);
                        });
                    }
                });
            });
        }

        @Override
        public InternalConvertableBidirectionalBindable<T> withErrorHandler(Consumer<Throwable> handler) {
            this.errorHandler = handler;
            return this;
        }

    }

    public static <T> ConvertableBidirectionalBindable<T, ?> bind(final Component component, final String attribute) {
        return new InternalConvertableBidirectionalBindable<T>(component, attribute);
    }
}
