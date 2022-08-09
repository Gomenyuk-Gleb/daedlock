import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {

    static final Object objectsFirst = new Object();
    static final Object objectsSecond = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                synchronized (objectsFirst) {

                    try {
                        Thread.sleep(1);
                        System.out.println("first: " + Thread.currentThread().getState());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (objectsSecond) {
                        System.out.println("first: " + Thread.currentThread().getState());
                    }
                }
            }
        });

        Thread threadSecond = new Thread(() -> {
            while (true) {
                synchronized (objectsSecond) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("second: " + Thread.currentThread().getState());

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (objectsFirst) {
                        System.out.println("second: " + Thread.currentThread().getState());
                    }

                }
            }
        });
        
        thread.start();
        threadSecond.start();
    }
}
