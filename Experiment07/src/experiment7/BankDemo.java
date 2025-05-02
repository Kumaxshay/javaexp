package experiment7;

public class BankDemo {
	 public static void main(String[] args) {
	     Account myAccount = new Account(1000);

	     myAccount.deposit(500);
	     myAccount.withdraw(200);
	     myAccount.withdraw(1500); 
	     myAccount.displayBalance();
	 }
	}