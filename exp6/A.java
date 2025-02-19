package P;
public class A {
    public void publicmethod(){
        System.out.println("publicmethod:cann be accessible from anywhere");
    }
    protected  void protectedmethod(){
        System.out.println("protected method:can be accessible within the same package or subclasses");
    }

    void defaultmethod(){
        System.out.println("can be accessible within the same package");
    }
    private void privatemethod(){
        System.out.println("private method:it is accessible only in the same class");
    }
    //method to access private method within class 
    public void accessprivate(){
            privatemethod();
    }
}
