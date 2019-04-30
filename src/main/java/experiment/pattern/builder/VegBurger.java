package experiment.pattern.builder;

/**
 * @author : liulei
 **/
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public float price() {
        return 20.5f;
    }
}
