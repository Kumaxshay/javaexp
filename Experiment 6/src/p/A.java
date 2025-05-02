
package p;

public class A {
    public void publicMethod() {
        System.out.println("Public method: Accessible from anywhere.");
    }

    protected void protectedMethod() {
        System.out.println("Protected method: Accessible within the same package or through inheritance in another package.");
    }

    void defaultMethod() {
        System.out.println("Default method: Accessible only within the same package.");
    }

    private void privateMethod() {
        System.out.println("Private method: Accessible only within this class.");
    }
    
    public void accessPrivateMethod() {
        privateMethod(); 
        System.out.println("Private method accessed within the class.");
    }
}
