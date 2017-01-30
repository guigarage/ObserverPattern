package com.guigarage.examples;

import com.guigarage.observer.BasicProperty;

/**
 * Created by hendrikebbers on 30.01.17.
 */
public class OnChangedAndCallExample {

    public void update(String value) {
        System.out.println("The current value is: " + value);
    }

    public static void main(String[] args) {
        BasicProperty<String> property = new BasicProperty<>();
        OnChangedAndCallExample instance = new OnChangedAndCallExample();

        //Instead of calling:
        property.onChanged(e -> instance.update(e.getNewValue()));
        instance.update(property.getValue());

        //You can simply write:
        property.onChangedAndCall(e -> instance.update(e.getNewValue()));
    }

}
