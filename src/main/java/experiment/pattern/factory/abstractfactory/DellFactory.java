package experiment.pattern.factory.abstractfactory;

/**
 * @author : liulei
 **/
public class DellFactory extends AbstarctFactory {
    @Override
    public PC createPC() {
        return new DellPC();
    }

    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
