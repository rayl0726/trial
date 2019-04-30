package experiment.pattern.builder;

/**
 * @author : liulei
 **/
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public float price() {
        return 22.2f;
    }
}
