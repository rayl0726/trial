package experiment.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * java 原生动态代理
 * @author : liulei
 **/
public class DynamicProxyHandler implements InvocationHandler {
    private DoSomething doSomething;

    public DynamicProxyHandler(DoSomething doSomething) {
        this.doSomething = doSomething;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method:" + method.getName());
        System.out.println("args:" + args[0].getClass().getName());
        System.out.println("before invoke method");
        Object invoke = method.invoke(doSomething, args);
        System.out.println("after invoke method");
        return invoke;
    }

    public static void main(String[] args) {
        DoSomething hello = (DoSomething) Proxy.newProxyInstance(
                DoSomething.class.getClassLoader(),
                new Class[] {DoSomething.class},
                new DynamicProxyHandler(new Hello()));
        hello.doSomething("tom");
    }
}
