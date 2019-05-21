package experiment.jvm.outofmemory;

/**
 * VM Args: -Xss2M
 *
 * 慎跑，容易卡死
 *
 * @author : liulei
 **/
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while(true) {
            Thread thread = new Thread(()->{
                dontStop();
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
