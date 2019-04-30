package experiment.executor;

import experiment.CommonT;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author : liulei
 **/
public class ExecutorServiceTest {
    static class Call implements Callable<Integer> {
        private int count;
        public Call(int count) {
            this.count = count;
        }
        @Override
        public Integer call() throws Exception {
            Thread.sleep((count + 1) * 1000 );
            System.out.println("call " + count + " finish");
            return count + 10;
        }
    }


    public static void main(String[] args) throws Exception{
        List<Callable<Integer>> tasks = new ArrayList<>(10);
        //并行执行。执行一个获取一个的结果
//        List<Future<Integer>> futures = new ArrayList<>(10);
//        for (int i = 0; i < 10; i++) {
//            tasks.add(new Call(i));
//        }
//
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        CompletionService<Integer> completionService = new ExecutorCompletionService(executorService);
//        for (Callable<Integer> temp: tasks) {
//            futures.add(completionService.submit(temp));
//        }
//
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println(completionService.take().get());
//        }

        //串行执行， 结果需所有的都执行完才能获取
        for (int i = 0; i < 10; i++) {
            tasks.add(new Call(i));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        System.out.println("finish");
        List<Future<Integer>> futures = executorService.invokeAll(tasks, 20, TimeUnit.SECONDS);
        for (Future<Integer> temp: futures) {
            System.out.println(temp.get());
        }
        executorService.shutdown();
    }
}
