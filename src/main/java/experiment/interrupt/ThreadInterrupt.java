package experiment.interrupt;


/**
 * @author : liulei
 **/
public class ThreadInterrupt {

    public static class SleepThread implements Runnable {

        @Override
        public void run() {

            try {
                System.out.println("sleep start");

                work2();

                System.out.println("sleep stop");
            } catch (InterruptedException e) {
                System.out.println("thread interrupt error: " + e.getMessage());
            }

        }


        public void work2() throws InterruptedException {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().isInterrupted());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("C isInterrupted()=" + Thread.currentThread().isInterrupted());
                    Thread.sleep(2000);
                    System.out.println("D isInterrupted()=" + Thread.currentThread().isInterrupted());
                }
                Thread.sleep(1000);
            }
        }
    }


    public static void main(String[] args) {
        SleepThread st = new SleepThread();
        Thread t1 = new Thread(st, "sleepThread");
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt();
    }
}
