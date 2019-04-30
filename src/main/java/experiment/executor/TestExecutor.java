package experiment.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author : liulei
 **/
public class TestExecutor {
    private static final int count = 100;
    private static final Executor executor = Executors.newFixedThreadPool(count);

    public static void main(String[] args) {
        while(true) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println("Thread finish");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(runnable);
        }
    }
}
