package experiment.pattern.tamplate;

/**
 * @author : liulei
 **/
public class Client {
    public static void main(String[] args) {
        Hammer h1 = new H1();
        Hammer h2 = new H2();
        h1.run();
        h2.run();
    }
}
