package experiment8;

public class OrderSystem {
public static void main(String[] args) {
   OrderProcessor orderProcessor = new OrderProcessor();
   
   try {
       orderProcessor.processOrder(500);
       orderProcessor.processOrder(0); 
   } catch (InvalidOrderAmountException e) {
       System.out.println("Error: " + e.getMessage()); 
   }
}
}