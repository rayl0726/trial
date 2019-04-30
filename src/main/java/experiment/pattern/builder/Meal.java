package experiment.pattern.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : liulei
 **/
public class Meal {
    private static List<Item> list = new ArrayList<>();

    public Meal addItem(Item item) {
        list.add(item);
        return this;
    }

    public float getPrice() {
        float cost = 0.0f;
        for (Item temp : list) {
            cost = cost + temp.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item temp : list) {
            System.out.println(temp.name());
            System.out.println(temp.packing().pack());
            System.out.println(temp.price());
            System.out.println("------------------------------------");
        }
    }

//    public static Meal.Builder build() {
//        return new Builder();
//    }

//    public static class Builder {
//
//        public static Builder addItem(Item item) {
//            list.add(item);
//            return Meal.build();
//        }

//        public Meal build() {
//            return new Meal();
//        }
//    }
}
