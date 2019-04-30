package experiment.pattern.decorator;

/**
 * @author : liulei
 **/
public abstract class ShapeDecorator implements Shape {

    protected Shape decoratorShape;

    ShapeDecorator(Shape decoratorShape) {
        this.decoratorShape = decoratorShape;

    }

    @Override
    public void draw() {
        decoratorShape.draw();
    }
}
