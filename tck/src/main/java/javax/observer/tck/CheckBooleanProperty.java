package javax.observer.tck;

import javax.observer.Property;
import javax.observer.Subscription;
import java.util.Objects;

/**
 * Created by hendrikebbers on 11.11.16.
 */
public class CheckBooleanProperty {

    public static void checkBooleanPropertyBasicMethods(final Property<Boolean> property) {
        Objects.requireNonNull(property);
        property.getValue();
        property.value();
        property.value().orElse(true);
        property.setValue(Boolean.FALSE);
        property.setValue(Boolean.TRUE);
        final Subscription subscription = property.onChanged(e -> new RuntimeException("FAIL"));
        subscription.unsubscribe();
        property.setValue(Boolean.FALSE);
        property.setValue(Boolean.TRUE);
    }

    public static void checkBooleanPropertyChangeFromTrueToFalse(final Property<Boolean> property) throws BadImplementationException {
        CheckBooleanObservable.checkBooleanObservableChangeFromTrueToFalse(property, b -> property.setValue(b));
    }
}
