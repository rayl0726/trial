package experiment.concurrent.concurrentutils;

import java.util.concurrent.CountDownLatch;

/**
 * @author : liulei
 **/
public class TestRunnable implements Runnable{

    private CountDownLatch latch;

    private String name;

    public TestRunnable(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[" + name + "] Running ! [countDownLatch.getCount() = " + latch.getCount() + "].");
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        int count = 5;
        CountDownLatch latch = new CountDownLatch(6);

        for (int i =0; i < count; i++) {
            new Thread(new TestRunnable(latch, "sub Thread" + (i + 100))).start();
        }

        System.out.println("main Thread block.");
        latch.await();
        System.out.println("sub Threads finish.");
    }
}
