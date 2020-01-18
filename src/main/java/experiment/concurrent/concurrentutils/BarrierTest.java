package experiment.concurrent.concurrentutils;

import java.util.concurrent.CyclicBarrier;

/**
 * @author : liulei
 **/
public class BarrierTest {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3);
        BarrierWorker worker1 = new BarrierWorker(barrier, "tom", 5000);
        BarrierWorker worker2 = new BarrierWorker(barrier, "jerry", 3000);
        BarrierWorker worker3 = new BarrierWorker(barrier, "lucky", 10000);

        Thread thread1 = new Thread(worker1);
        Thread thread2 = new Thread(worker2);
        Thread thread3 = new Thread(worker3);
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("master");
    }
}
