package experiment.pattern.builder;

/**
 * @author : liulei
 **/
public abstract class Drink implements Item{
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}
