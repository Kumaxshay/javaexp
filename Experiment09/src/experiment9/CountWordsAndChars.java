package experiment9;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class CountWordsAndChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  
        System.out.print("Enter the filename: ");
        String filename = scanner.nextLine();  

        int wordCount = 0;
        int charCount = 0;

        try (FileInputStream file = new FileInputStream(filename)) {
            int character;
            StringBuilder word = new StringBuilder();
            while ((character = file.read()) != -1) {  
                charCount++;  
                if (Character.isWhitespace(character)) { 
                    if (word.length() > 0) {
                        wordCount++;  
                        word.setLength(0);  
                    }
                } else {
                    word.append((char) character); 
                }
            }
            if (word.length() > 0) {
                wordCount++;
            }

            System.out.println("Total words: " + wordCount);
            System.out.println("Total characters: " + charCount);

        } catch (IOException e) {
            System.out.println("Error: Could not read the file.");
        }
    }
}
