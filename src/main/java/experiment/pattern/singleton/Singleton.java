package experiment.pattern.singleton;

/**
 * @author : liulei
 **/
public class Singleton {
    //线程安全，当两个线程同时getInstance 时 可能都为空，会创建两个实例
//    private static Singleton instance = null;
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        if (instance == null) {
//            instance = new Singleton();
//        }
//        return instance;
//    }

    //线程安全，效率低下
//    private static volatile Singleton instance = null;
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        synchronized (instance) {
//            if (instance == null) {
//                instance = new Singleton();
//            }
//            return instance;
//        }
//    }

    // volatile 可以保证可见性，同时还有防止指令重排序的功能（jdk1.5以后）
    //多一个判断用来防止每次getInstance都阻塞
//    private static volatile Singleton instance = null;
//    private Singleton() {
//    }
//
//    public static Singleton getInstance() {
//        if(instance == null) {
//            synchronized (instance) {
//                if (instance == null) {
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//    }

    //延迟初始化 避免同步开销，
//    private static Singleton instance = new Singleton();
//
//    public static Singleton getInstance() {
//        return instance;
//    }

    //静态类调用的时候才加载，比上面的节省了资源
    private static class InstanceHolder {
        private static Singleton instance = new Singleton();

    }
    public static Singleton getInstance() {
        return InstanceHolder.instance;
    }

}
