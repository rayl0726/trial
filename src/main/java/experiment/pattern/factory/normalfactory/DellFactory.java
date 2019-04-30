package experiment.pattern.factory.normalfactory;

/**
 * @author : liulei
 **/
public class DellFactory implements IFactory {
    @Override
    public PC createPC() {
        return new DellPC();
    }
}
