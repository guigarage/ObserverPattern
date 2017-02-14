package com.guigarage.examples;

import com.guigarage.binding.Binding;
import com.guigarage.observer.BasicProperty;

import javax.observer.Property;

public class BindingExample {

    public static void main(String[] args) {
        Property<String> stringProperty1 = new BasicProperty<>();
        Property<String> stringProperty2 = new BasicProperty<>();

        Property<Boolean> booleanProperty1 = new BasicProperty<>();
        Property<Boolean> booleanProperty2 = new BasicProperty<>();

        Property<Integer> intProperty1 = new BasicProperty<>();
        Property<Integer> intProperty2 = new BasicProperty<>();

        Binding.bind(stringProperty1).bidirectionalTo(stringProperty2);
        Binding.bind(booleanProperty1).bidirectionalTo(booleanProperty2);
        Binding.bind(intProperty1).bidirectionalTo(intProperty2);

        Binding.bind(stringProperty1).to(stringProperty2);
        Binding.bind(booleanProperty1).to(booleanProperty2);
        Binding.bind(intProperty1).to(intProperty2);


        Binding.bind(stringProperty1).to(booleanProperty1, b -> b.toString());
        Binding.bind(stringProperty1).withErrorHandler(e -> e.printStackTrace()).to(booleanProperty1, b -> b.toString());

        Binding.bind(stringProperty1).bidirectionalTo(booleanProperty1, b -> b.toString(), s -> Boolean.valueOf(s));

    }

}
