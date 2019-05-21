package experiment.concurrent.deadLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : liulei
 **/
public class DeadLock {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    private static final ReentrantLock lock = new ReentrantLock();

    public static void DeadLockBySync() {
        Thread t1 = new Thread(()->{
            synchronized (lock1) {
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock1 ing");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " after sleep 2s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " get lock2 ing");
                }
            }
        }, "Thread1");

        Thread t2 = new Thread(()->{
            synchronized (lock2) {
                try {
                    System.out.println(Thread.currentThread().getName() + " get lock2 ing");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " after sleep 2s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " get lock1 ing");
                }
            }
        }, "Thread2");
        t2.start();
        t1.start();
    }

    public static void DeadLockByLcok() {
        A a = new A();
        B b = new B();
        Thread t1 = new Thread(()->{
            a.A(b);
        }, "ThreadA");

        Thread t2 = new Thread(()->{
            b.B(a);
        }, "ThreadB");
        t1.start();
        t2.start();
    }

    static class A {
        private ReentrantLock lock = new ReentrantLock();
        public void A1() {
            System.out.println(Thread.currentThread().getName() + "  A1 start lock");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " execute A1");
            lock.unlock();
        }

        public void A(B b) {
            System.out.println(Thread.currentThread().getName() + " start lock");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " locking");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            b.B1();
            lock.unlock();
        }
    }

    static class B {
        private ReentrantLock lock = new ReentrantLock();

        public void B1() {
            System.out.println(Thread.currentThread().getName() + "  B1 start lock");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " execute B1");
            lock.unlock();
        }

        public void B(A a) {
            System.out.println(Thread.currentThread().getName() + " start lock");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " locking");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.A1();
            lock.unlock();
        }
    }
    public static void main(String[] args) {

        DeadLockByLcok();
    }
}
