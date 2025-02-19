class mathsconstant{
    final double PI=3.14;
    
final void displayPI(){
    System.out.println("value of PI:"+PI);
}
}

class circle extends mathsconstant{
    void calculatearea(double radius){
        double area=radius*radius*PI;
        System.out.println("area of the circle is:"+area);
    }
}
public class Constants{
    public static void main(String[] args) {
        circle c1= new circle();
        c1.displayPI();
        c1.calculatearea(5);

        // void displayPI(){
            // System.out.println("overriding");
        // }

        // circle.PI=3; MODIFYING FINAL VARIABLE



    }
}
