package experiment.pattern.factory.abstractfactory;

/**
 * @author : liulei
 **/
public class HPPCFactory extends AbstarctFactory {
    @Override
    public PC createPC() {
        return new HPPC();
    }

    @Override
    public Mouse createMouse() {
        return new HPMouse();
    }
}
