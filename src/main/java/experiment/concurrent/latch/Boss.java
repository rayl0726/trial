package experiment.concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author : liulei
 **/
public class Boss implements Runnable {
    private CountDownLatch latch;
    public Boss(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        System.out.println("boss waiting");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("boss start check");

    }
}
