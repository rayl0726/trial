package experiment.concurrentutils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : liulei
 **/
public class BarrierWorker implements Runnable {
    private CyclicBarrier barrier;
    private String name;
    private int time;

    public BarrierWorker(CyclicBarrier barrier, String name, int time) {
        this.barrier = barrier;
        this.name = name;
        this.time = time;
    }
    @Override
    public void run() {
        System.out.println(name + " working ");
        try {
            Thread.sleep(time);
            System.out.println(name + " work finish");
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(name + ": fuck! go on work");
    }
}
