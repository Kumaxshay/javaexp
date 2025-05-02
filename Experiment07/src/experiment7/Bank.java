package experiment7;

interface Bank {
 void deposit(double amount);
 void withdraw(double amount);
}

class Account implements Bank {
 private double balance;

 public Account(double initialBalance) {
     this.balance = initialBalance;
 }

 @Override
 public void deposit(double amount) {
     if (amount > 0) {
         balance += amount;
         System.out.println("Deposited: " + amount);
     } else {
         System.out.println("Deposit amount must be positive.");
     }
 }

 @Override
 public void withdraw(double amount) {
     if (amount > 0 && amount <= balance) {
         balance -= amount;
         System.out.println("Withdrawn: " + amount);
     } else {
         System.out.println("Insufficient balance or invalid amount.");
     }
 }

 public void displayBalance() {
     System.out.println("Current Balance: " + balance);
 }
}



