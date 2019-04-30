package experiment.pattern.builder;

/**
 * @author : liulei
 **/
public class Coke extends Drink {
    @Override
    public String name() {
        return "Coke Drink";
    }

    @Override
    public float price() {
        return 4.1f;
    }
}
