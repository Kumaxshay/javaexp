package experiment7;

public class EmployeeTester {
    public static void main(String[] args) {
        Employee manager = new Manager("Nipun", 50000);
        Employee developer = new Developer("Aniket", 50, 1600);

        manager.displayDetails();
        manager.calculateSalary();

        developer.displayDetails();
        developer.calculateSalary();
    }
}
