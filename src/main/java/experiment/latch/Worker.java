package experiment.latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author : liulei
 **/
public class Worker implements Runnable{
    private CountDownLatch latch;
    private String name;
    private int time;

    public Worker(CountDownLatch latch, String name, int time) {
        this.latch = latch;
        this.name = name;
        this.time = time;
    }

    @Override
    public void run() {
        System.out.println(name + " doing work");
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " finish work");
        latch.countDown();
    }
}
