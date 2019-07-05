package utilities;

public class Waiter {
    public static void wait(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }
}
