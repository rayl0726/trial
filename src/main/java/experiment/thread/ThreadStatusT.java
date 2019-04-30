package experiment.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author : liulei
 **/
public class ThreadStatusT {

    //    private static ReentrantLock lock = new ReentrantLock();
    final static Object lock = new Object();


    public static class SleepThread implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("sleep start");

                Thread.sleep(600000);

                System.out.println("sleep stop");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public static class WaitThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                try {
                    System.out.println("wait start");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class ParkingThread implements Runnable {

        @Override
        public void run() {
            System.out.println("parking");
            LockSupport.park();
        }
    }


    public static class RunnableThread implements Runnable {

        @Override
        public void run() {
            long i = 0;
            while (true) {
                i++;
                System.out.println(i);
            }

        }
    }

    public static class BlockedThread implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("got lock");
                try {
                    Thread.sleep(1000L * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
//        SleepThread mt = new SleepThread();
//        Thread t1 = new Thread(mt,"sleep Thread");
//        t1.start();




//        BlockedThread bt = new BlockedThread();
//        Thread t3 = new Thread(bt, "block Thread");
//        t3.start();

//        WaitThread wt = new WaitThread();
//        Thread t4 = new Thread(wt, "wait Thread");
//        t4.start();

        ParkingThread pt = new ParkingThread();
        Thread t5 = new Thread(pt, "parking thread");
        t5.start();


        RunnableThread rt = new RunnableThread();
        Thread t2 = new Thread(rt, "Runnable Thread");
        t2.start();

    }


}
