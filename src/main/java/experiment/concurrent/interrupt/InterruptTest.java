package experiment.concurrent.interrupt;

/**
 * @author : liulei
 **/
public class InterruptTest {

    public static void main(String[] args) {
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(100000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            public void cancel() {
//                Thread.currentThread().interrupt();
//            }
//
//        };
//        t.start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });
        thread.setName("test");
        thread.start();
        try {
            Thread.sleep(5000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(thread.isInterrupted());


    }
}
