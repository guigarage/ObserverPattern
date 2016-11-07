package javax.observer.examples.timer;

import javax.observer.examples.BasicObservable;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;

public class TimeObserver extends BasicObservable<LocalDateTime> {

    public TimeObserver() {
        updateValue(LocalDateTime.now());
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                updateValue(LocalDateTime.now());
            }
        });
    }

    public static void main(String[] args) {
        TimeObserver observer = new TimeObserver();
        observer.onChanged(e -> System.out.println(e.getNewValue()));
    }

}
