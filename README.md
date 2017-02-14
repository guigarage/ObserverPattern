# Observer Pattern for Java

This repository contains a generic definition of the observer pattern for Java.

## Why do I need this?

Currently Java only contains the `java.util.Observable` class that is part since Java 1 and it's
functionality and API don't use modern Java languages features and APIs like generics, functional
interfaces or Optional. In additional this API will become deprecated in Java 9.
Based on this it's quite hard to work with this implementation and mostly
all modern Java framework don't use it. Instead custom implementations of the observer pattern
can be found in mostly all frameworks. Even in JavaSE you can find custom implementations of the
pattern as the JavaFX properties (see `javafx.beans.Observable`).
Based on this distribution it's quite hard to understand the differences of all the implementations
and combine several of them in a single project. 

## How to use it today?

Currently this repository only provides interfaces for the observer pattern like a JSR would do. The
plan of this repository is to discuss the definition of such a pattern in Java and find a solution
that could work as a default specification. Maybe a first default implementation will be added but in
the end the plan is, that framework developers depend on the interfaces that are provided by this
repository to provide a unique observer API for Java.

## How does the API look?

The API provide observer support for values and collections. 

TODO: The API has changed with the last PRs and this document must be updated.

### Working with values

Since Java do not provide a native observer
support values must be wrapped in a class to support the observer pattern. Therefore the API provides the
`com.guigarage.observer.Observable` interface. This interface offers the possibility to add listener that
will be triggered whenever the internal value will change:

```java
Observable<Date> observer = ...
observer.onChanged(e -> System.out.println("Value has changed!"));
```

Instead of having `addListener(...)` and `removeListener(...)` methods like in several Java APIs the 
`onChange(...)` methods returns a `com.guigarage.observer.Subscription` instance that can be used to remove
the listener:


```java
Observable<Date> observer = ...
Subscription subscription = observer.onChanged(e -> System.out.println(e.getNewValue()));

//later
subscription.unsubscribe();
```

### Working with collections

The API provides the 2 base interfaces `com.guigarage.observer.collection.ObservableList` and
`com.guigarage.observer.collection.ObservableSet` that can be used to provide the observer pattern 
for collections. The following examle shows how an observable list can be used:

```java
ObservableList<String> list = ...
Subscription subscription = list.onChanged(e -> System.out.println("Content of list has changed"));

//later
subscription.unsubscribe();
```
