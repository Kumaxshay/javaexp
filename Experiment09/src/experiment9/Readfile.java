package experiment9;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Readfile {
    public static void main(String[] args) {
        try {
            FileReader file = new FileReader("student.txt");  
            BufferedReader reader = new BufferedReader(file);  
            String line;
            while ((line = reader.readLine()) != null) { 
                System.out.println(line);  
            }
            reader.close(); 

        } catch (FileNotFoundException e) {
            System.out.println("Error: The file student.txt was not found.");  
        } catch (IOException e) {
            System.out.println("Error: There was an issue reading the file.");  
        }
    }
}
