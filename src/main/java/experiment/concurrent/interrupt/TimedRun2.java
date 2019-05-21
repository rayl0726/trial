package experiment.concurrent.interrupt;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : liulei
 **/
public class TimedRun2 {
    private static final ScheduledExecutorService cancelExec = Executors
            .newScheduledThreadPool(1);

    public void timedRun(final Runnable r, long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;
            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }

            private void rethrow() {
                if (t != null) {
                    try {
                        throw launderThrowable(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.setName("taskThread");
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
                System.out.println("1--" + taskThread.getName() + ":" + taskThread.isInterrupted());
            }
        }, timeout, unit);
//        taskThread.join(unit.toMillis(timeout));
        taskThread.join(100);
        task.rethrow();
        System.out.println("2--" + taskThread.getName() + ":" + taskThread.isInterrupted());
    }

    public static Exception launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }



    public static void main(String[] args) {
        TimedRun2 timeRun = new TimedRun2();
        Runnable run = new Runnable() {

            @Override
            public void run() {
                int i = 0;
                for (int j = 0; j < 100000000; j++) {
//                    try {
//                        Thread.sleep(1000);
//                        System.out.println(j);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    i++;
                    if (i % 10000000 == 0) {
                        System.out.println(i + "  "+ Thread.currentThread().getName());
                    }
                }
            }
        };
        try {
            timeRun.timedRun(run, 100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
