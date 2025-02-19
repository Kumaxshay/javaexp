// Final class that cannot be extended
final class Logger {
    void logMessage(String message) {
        System.out.println("Log: " + message);
    }
}

// Attempting to extend Logger will cause a compilation error
// class ExtendedLogger extends Logger { // ❌ Error: Cannot inherit from final class
//     void logMessage(String message) {
//         System.out.println("Extended Log: " + message);
//     }
// }

public class login {
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.logMessage("This is a message.");
    }
}
