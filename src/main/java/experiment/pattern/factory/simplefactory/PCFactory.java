package experiment.pattern.factory.simplefactory;

/**
 * @author : liulei
 **/
public class PCFactory {

    public PC createPC(String name) {
        if ("dell".equals(name)) {
            return new DellPC();
        } else if ("hp".equals(name)) {
            return new HPPC();
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
        PCFactory factory = new PCFactory();
        PC dell = factory.createPC("dell");
        dell.getName();
    }
}
