package com.guigarage.observer;

/**
 * TODO
 * @author Hendrik Ebbers
 */
@FunctionalInterface
public interface Subscription {

    void unsubscribe();
}
