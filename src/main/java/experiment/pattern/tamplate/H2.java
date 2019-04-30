package experiment.pattern.tamplate;

/**
 * @author : liulei
 **/
public class H2 extends Hammer {
    @Override
    protected void start() {
        System.out.println("H2启动...");
    }

    @Override
    protected void stop() {
        System.out.println("H2停止...");
    }

    @Override
    protected void alarm() {
        System.out.println("H2鸣笛...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("H2引擎轰鸣...");
    }
}
