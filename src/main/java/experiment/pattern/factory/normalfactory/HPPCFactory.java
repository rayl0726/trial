package experiment.pattern.factory.normalfactory;

/**
 * @author : liulei
 **/
public class HPPCFactory implements IFactory {
    @Override
    public PC createPC() {
        return new HPPC();
    }
}
