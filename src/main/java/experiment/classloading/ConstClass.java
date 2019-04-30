package experiment.classloading;

/**
 * @author : liulei
 **/
public class ConstClass {
    static {
        System.out.println("Constlass init");
    }
    public static final String HELLOWORLD = "Hello world";
}
