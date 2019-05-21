package experiment.concurrent.thread;

/**
 * 测试 threadLocal 的使用
 *
 * @author : liulei
 **/
public class ThreadLocalT {

    public static class MyThread implements Runnable {

        private ThreadLocal t1 = new ThreadLocal();

        @Override
        public void run() {
            t1.set((int)(Math.random() * 100D));
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(t1.get());
        }
    }

    public static void main(String[] args) {
        MyThread shareInstance = new MyThread();
        Thread t1 = new Thread(shareInstance);
        Thread t2 = new Thread(shareInstance);
        t1.start();
        t2.start();
    }
}
