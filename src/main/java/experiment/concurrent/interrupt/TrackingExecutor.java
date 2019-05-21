package experiment.concurrent.interrupt;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : liulei
 **/
public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exec;
    private final Set<Runnable> tasksCancelledAtShutdown = Collections
            .synchronizedSet(new HashSet<Runnable>());

    public TrackingExecutor(ExecutorService exec){
        this.exec = exec;
    }

    public List<Runnable> getCancelledTasks(){
        if(!exec.isTerminated())
            throw new IllegalStateException();
        return new ArrayList<Runnable>(tasksCancelledAtShutdown);
    }

    /**
     *
     * @Description: TODO
     * @param
     * @author xingle
     * @data 2014-11-13 上午9:06:56
     */
    @Override
    public void execute(final Runnable runnable) {
        exec.execute(new Runnable() {

            @Override
            public void run() {
                try{
                    runnable.run();
                }finally{
                    if(isShutdown() && Thread.currentThread().isInterrupted())
                        tasksCancelledAtShutdown.add(runnable);
                }
            }
        });
    }

    /**
     * 下面将ExecutorService 的其他方法委托给 exec
     */

    /**
     *
     * @Description: TODO
     * @author xingle
     * @data 2014-11-13 上午9:06:56
     */
    @Override
    public void shutdown() {
        exec.shutdown();
    }

    /**
     *
     * @Description: TODO
     * @return
     * @author xingle
     * @data 2014-11-13 上午9:06:56
     */
    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    /**
     *
     * @Description: TODO
     * @return
     * @author xingle
     * @data 2014-11-13 上午9:06:56
     */
    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    /**
     *
     * @Description: TODO
     * @return
     * @author xingle
     * @data 2014-11-13 上午9:06:56
     */
    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    /**
     *
     * @Description: TODO
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     * @author xingle
     * @data 2014-11-13 上午9:06:56
     */
    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        return exec.awaitTermination(timeout, unit);
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        TrackingExecutor executor = new TrackingExecutor(service);
        for (int i = 0; i < 10; i++) {
            executor.execute(()->{

            });
        }
    }
}
