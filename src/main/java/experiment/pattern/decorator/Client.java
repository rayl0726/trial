package experiment.pattern.decorator;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : liulei
 **/
public class Client {
    public static void main(String[] args) {
        Shape circle = new Circle();
        circle.draw();
        System.out.println("___________________________________");
        Shape redCircle = new RedShapeDecorator(new Circle());
        redCircle.draw();

//        Shape rectangle = new Rectangle();
//        rectangle.draw();
//
//        Shape redRectangle = new RedShapeDecorator(new Rectangle());
//        redRectangle.draw();

    }
}
