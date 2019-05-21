package experiment.jvm.classloading;

/**
 * @author : liulei
 **/
public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init");
    }
}
