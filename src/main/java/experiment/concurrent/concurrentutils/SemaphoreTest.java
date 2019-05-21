package experiment.concurrent.concurrentutils;

import java.util.concurrent.Semaphore;

/**
 * @author : liulei
 **/
public class SemaphoreTest {

    private static Semaphore sem;

    private static String a = "init";

    public static void main(String[] args) {

        sem = new Semaphore(1);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sem.acquire();
                    Thread.sleep(5000);
                    if(a.equals("init")) {
                        a = "test";
                        System.out.println("set a success");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread1.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hold sem");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sem.release();
                System.out.println("release sem");

            }
        });
        thread3.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sem.acquire();
                    if(a.equals("test")) {
                        System.out.println("thread2 success");
                    } else {
                        System.out.println("thread2 failed");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }
}
