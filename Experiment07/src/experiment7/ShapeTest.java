package experiment7;

public class ShapeTest {
    public static void main(String[] args) {

        Shape rect = new Rectangle(5, 10);
        Shape circle = new Circle(7);

        rect.calculateArea();
        circle.calculateArea();
    }
}