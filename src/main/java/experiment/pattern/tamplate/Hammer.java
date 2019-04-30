package experiment.pattern.tamplate;

/**
 * @author : liulei
 **/
public abstract class Hammer {

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    public final void run(){
        this.start();
        this.engineBoom();
        this.alarm();
        this.stop();
    }
}
