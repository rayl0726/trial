package experiment.concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 * @author : liulei
 **/
public class LatchTest {
    /**
     * boss 只有在三个人都工作完成后才检查工作
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Worker worker1 = new Worker(latch, "tom", 5000);
        Worker worker2 = new Worker(latch, "jerry", 3000);
        Worker worker3 = new Worker(latch, "lucky", 10000);
//        Boss boss = new Boss(latch);

        new Thread(worker1).start();
        new Thread(worker2).start();
        new Thread(worker3).start();

        latch.await();
        System.out.println("main start");
//        new Thread(boss).start();



    }
}
