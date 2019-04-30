package experiment.deadLock;

import java.util.concurrent.*;

/**
 * @author : liulei
 **/
public class ThreadDeadlock {
    ExecutorService exec = Executors.newSingleThreadScheduledExecutor();
//    ExecutorService exec = Executors.newFixedThreadPool(2);
    //线程饥饿死锁 由于RenderPageTask 本身是一个任务， 而Executor中只有一个线程， 这样页眉页脚的渲染无法执行，本身也在等待就产生了死锁
    class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> header,footer;

            header = exec.submit(new Callable<String>(){
                @Override
                public String call() throws Exception {
                    System.out.println("加载页眉");
                    Thread.sleep(2*1000);
                    return "页眉";
                }
            });


            footer = exec.submit(new Callable<String>(){
                @Override
                public String call() throws Exception {
                    System.out.println("加载页脚");
                    Thread.sleep(3*1000);
                    return "页脚";
                }
            });

            System.out.println("渲染页面主体");

            return header.get() + footer.get();
        }

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadDeadlock td = new ThreadDeadlock();
        Future<String>  futre = td.exec.submit(td.new RenderPageTask());
        String result = futre.get();
        System.out.println("执行结果为："  +  result);

    }
}
