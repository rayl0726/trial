package experiment.pattern.builder;

/**
 * @author : liulei
 **/
public class Pepsi extends Drink {
    @Override
    public String name() {
        return "Pepsi Drink";
    }

    @Override
    public float price() {
        return 5.5f;
    }
}
