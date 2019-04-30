package experiment.pattern.proxy;

/**
 * @author : liulei
 **/
public class Hello implements DoSomething {
    @Override
    public void doSomething(String str) {
        System.out.println(str + " says Hello");
    }
}
