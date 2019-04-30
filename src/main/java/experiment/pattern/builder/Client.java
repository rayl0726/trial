package experiment.pattern.builder;

/**
 * @author : liulei
 **/
public class Client {

    public static void main(String[] args) {
        Meal meal = new Meal();
        meal.addItem(new VegBurger()).addItem(new Pepsi());
        meal.showItems();
    }
}
