package experiment.concurrent.clhlock;

/**
 * @author : liulei
 **/
public class MCSLock {
    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean waiting = true;
    }

    volatile MCSNode queue;
}
