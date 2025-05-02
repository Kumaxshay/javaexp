package experiment9;

import java.io.*;
import java.util.Scanner;

public class FileReader {
    
    public static void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner fileScanner = new Scanner(file);
        
        while (fileScanner.hasNextLine()) {
            System.out.println(fileScanner.nextLine());
        }
        
        fileScanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = scanner.nextLine();
        
        try {
            readFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + fileName);
        } finally {
            System.out.println("File operation attempted");
        }
        
        scanner.close();
    }
}
