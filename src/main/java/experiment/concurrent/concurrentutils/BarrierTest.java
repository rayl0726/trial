package experiment.concurrent.concurrentutils;

import java.util.concurrent.CyclicBarrier;

/**
 * @author : liulei
 **/
public class BarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        BarrierWorker worker1 = new BarrierWorker(barrier, "tom", 5000);
        BarrierWorker worker2 = new BarrierWorker(barrier, "jerry", 3000);
        BarrierWorker worker3 = new BarrierWorker(barrier, "lucky", 10000);

        new Thread(worker1).start();
        new Thread(worker2).start();
        new Thread(worker3).start();
    }
}
