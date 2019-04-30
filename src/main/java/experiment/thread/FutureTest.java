package experiment.thread;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * @author : liulei
 **/
public class FutureTest {

    public static CompletableFuture createFuture() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            for(int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("future sleep " + i  + " s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("run end ...");
        });
        return future;
    }

    public static void whenComplete() throws Exception {
        createFuture().whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void v, Throwable action) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完成！");
            }

        });
        createFuture().whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void v, Throwable action) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完成！");
            }

        });

        createFuture().whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void v, Throwable action) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完成！");
            }

        });

    }


    public static void whenCompleteAsync() throws Exception {

        createFuture().whenCompleteAsync(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void v, Throwable throwable) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完成！");
            }
        }) ;
        createFuture().whenCompleteAsync(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void v, Throwable throwable) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完成！");
            }
        }) ;
        createFuture().whenCompleteAsync(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void v, Throwable throwable) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("执行完成！");
            }
        }) ;
    }

    public static void main(String[] args) throws Exception{
//        whenComplete();
        whenCompleteAsync();
        Thread.sleep(20000);
    }
}
