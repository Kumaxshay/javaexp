package experiment8;


class InvalidOrderAmountException extends Exception {
	 private static final long serialVersionUID = 1L;
 public InvalidOrderAmountException(String message) {
     super(message);
 }
}

class OrderProcessor {
 public void processOrder(double amount) throws InvalidOrderAmountException {
     if (amount <= 0) {
         throw new InvalidOrderAmountException("Order amount must be positive!");
     }
     System.out.println("Order processed successfully for amount: " + amount);
 }
}


