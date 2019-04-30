package experiment.pattern.builder.example2;

/**
 * @author : liulei
 **/
public class Container {
    private int age;
    private String name;
    private double price;

    private Container(Builder builder) {
        this.age = builder.age;
        this.name = builder.name;
        this.price = builder.price;
    }

    @Override
    public String toString() {
        return "Container{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static class Builder {
        private int age;
        private String name;
        private double price;

        private Builder() {};

        public Builder addAge(int age) {
            this.age = age;
            return this;
        }

        public Builder addName(String name) {
            this.name = name;
            return this;
        }

        public Builder addPrice(double price) {
            this.price = price;
            return this;
        }

        public Container build() {
            return new Container(this);
        }
    }

    public static void main(String[] args) {
        Container tom = new Builder().addAge(10).addName("tom").addPrice(10.2).build();
        System.out.println(tom);
    }
}
