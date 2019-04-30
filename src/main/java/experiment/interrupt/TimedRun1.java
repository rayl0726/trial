package experiment.interrupt;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : liulei
 **/
public class TimedRun1 {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    public void timedRun(Runnable r,long timeout, TimeUnit unit) {
        final Thread taskThread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
                System.out.println("1--" + taskThread.getName() + ":" + taskThread.isInterrupted());
            }
        }, timeout, unit);
        r.run();
        System.out.println("2--" + taskThread.getName() + " : " + taskThread.isInterrupted());
    }

    public static void main(String[] args) {
        TimedRun1 timeRun1 = new TimedRun1();
        Runnable run = new Runnable() {

            @Override
            public void run() {
                int i = 0;
                for (int j = 0; j < 100000000; j++) {
                    i++;
                    if (i % 10000000 == 0) {
                        System.out.println(i + "  "+ Thread.currentThread().getName());
                    }
                }
            }
        };
        timeRun1.timedRun(run, 1, TimeUnit.MILLISECONDS);
    }
}
