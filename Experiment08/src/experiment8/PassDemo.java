package experiment8;

public class PassDemo {
	 public static void main(String[] args) {
	     String[] testPasswords = {"12345", "harsh", "20September", "zxcasdqwe"};
	     for (String password : testPasswords) {
	         try {
	             PasswordValidator.validatePassword(password);
	             System.out.println("Password '" + password + "' is strong.");
	         } catch (WeakPasswordException e) {
	             System.out.println("Weak password: " + e.getMessage());
	         }
	     }
	 }
	}
