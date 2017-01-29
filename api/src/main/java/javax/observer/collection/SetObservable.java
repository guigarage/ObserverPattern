/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.observer.collection;


import java.util.Set;

/**
 * Set observable. Set implementations that shall be observable should
 * implement this interface.
 *
 * @param <T> element type
 * 
 * @author Michael Hoffer (info@michaelhoffer.de)
 */
public interface SetObservable<T> extends CollectionObservable<T, Set<T>, SetChange<T>> {

}