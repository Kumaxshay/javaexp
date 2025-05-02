package experiment8;

//Custom Exception for Weak Password
class WeakPasswordException extends Exception {
	private static final long serialVersionUID = 1L;
 public WeakPasswordException(String message) {
     super(message);
 }
}

class PasswordValidator {
 public static void validatePassword(String password) throws WeakPasswordException {
     if (password.length() < 8 || !password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
         throw new WeakPasswordException("Password is too weak! It must be at least 8 characters long and include both letters and numbers.");
     }
 }
}



