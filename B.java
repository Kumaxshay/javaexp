package Q;
import P.A;
public class B extends A{
    public static void main(String[] args) {
        A obja =new A();
        obja.publicmethod();

        B objb =new B();
        objb.protectedmethod(); 

        // obja.defaultmethod();  it is not allowed for different package 
        
        // obja.privatemethod(); not accessible 
        // obja.accessprivate();  it is accessible 
    }
    
}
