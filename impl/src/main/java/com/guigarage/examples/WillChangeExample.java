package com.guigarage.examples;

import com.guigarage.observer.BasicProperty;

import javax.observer.Property;
import java.time.LocalDateTime;

/**
 * Created by hendrikebbers on 30.01.17.
 */
public class WillChangeExample {

    public static void main(String[] args) {

        //Defines a String property that only allows characters that are defined as digit
        Property<String> myProperty = new BasicProperty<>();
        myProperty.onWillChange(e -> {
            e.getNewValue().chars().filter(c -> !Character.isDigit(c)).findAny().ifPresent(c -> e.veto());
        });


        //Defines a property that can only hold future dates
        Property<LocalDateTime> myProperty2 = new BasicProperty<>();
        myProperty2.onWillChange(e -> {
            if (e.getNewValue().isBefore(LocalDateTime.now())) {
                e.veto();
            }
        });
    }

}
