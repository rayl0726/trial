package experiment.interrupt;

import java.util.concurrent.Callable;

/**
 * @author : liulei
 **/
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        while (true) {
            System.out.println("我在执行任务: Test 来自"+Thread.currentThread().getName()+"\n");
            Thread.sleep(100);
        }
    }
}
