package experiment.classloading;

/**
 * @author : liulei
 **/
public class SuperClass {
    static {
        System.out.println("superClass init");
    }

    public static int value = 123;
}
