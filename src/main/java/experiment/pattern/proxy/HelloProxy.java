package experiment.pattern.proxy;

/**
 * 静态代理
 * @author : liulei
 **/
public class HelloProxy implements DoSomething {
    private DoSomething doSomething = new Hello();
    @Override
    public void doSomething(String str) {
        System.out.println("before say doSomething");
        doSomething.doSomething(str);
        System.out.println("after say doSomething");
    }

    public static void main(String[] args) {
        HelloProxy helloProxy = new HelloProxy();
        helloProxy.doSomething("tom");
    }
}
