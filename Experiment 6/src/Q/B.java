package Q;
import p.A;

public class B extends A { 
    public static void main(String[] args) {
        A objA = new A();

        objA.publicMethod();

        B objB = new B();
        objB.protectedMethod();
        objA.accessPrivateMethod();
    }
}
