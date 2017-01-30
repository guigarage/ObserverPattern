package com.guigarage.examples;

import com.guigarage.binding.SwingBinding;
import com.guigarage.observer.BasicProperty;

import javax.observer.Property;
import javax.swing.JTextField;

public class SwingBindingExample {

    public static void main(String[] args) {

        Property<String> databaseBackgroundProperty = new BasicProperty<>();
        JTextField textField = new JTextField();

        SwingBinding.<String>bind(textField, "text").bidirectionalTo(databaseBackgroundProperty);
    }

}
