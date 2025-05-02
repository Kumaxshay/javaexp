package experiment8;

public class Calc {
     public int divide(int  n1 , int n2) {
    	 try {
    		 return n1/n2;
    	 }
    	 catch(Exception e) {
    		 System.out.println("That it is an arithmetic exception error ");
    		 return 0;
    	 }
     }
     	public static void main ( String [] args) {
     		   Calc rf = new Calc();
     		   int one = rf.divide(15, 0);
     		   System.out.println(one);
     	   }
}

