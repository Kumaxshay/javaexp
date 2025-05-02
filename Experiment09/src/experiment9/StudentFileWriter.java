package experiment9;

import java.io.*;
import java.util.Scanner;

public class StudentFileWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();
        
        System.out.print("Enter grade: ");
        String grade = scanner.next();
        
        String studentData = "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade + "\n";
        
        try (FileWriter writer = new FileWriter("student.txt", true)) { 
            writer.write(studentData);
            System.out.println("Student data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
        scanner.close();
    }
}
