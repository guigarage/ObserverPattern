package com.guigarage.observer;

@FunctionalInterface
public interface Subscription {

    void unsubscribe();
}
