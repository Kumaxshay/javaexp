package Q;

class MathConstants {
    final double PI = 3.14159;

    final void displayPI() {
        System.out.println("Value of PI: " + PI);
    }
}

class Circle extends MathConstants {
    
    void calculateArea(double radius) {
        double area = PI * radius * radius;
        System.out.println("Area of Circle with radius " + radius + " is: " + area);
    }

    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.displayPI();
        circle.calculateArea(5.0);
    }
}
