class Employee {
   
    int employeeID;
    String name;
    String department;
    private double salary;
    
    
    static int totalEmployees = 0;

  
    public Employee() {
        this.employeeID = 0;
        this.name = "Default Name";
        this.department = "Not Assigned";
        this.salary = 0.0;
        totalEmployees++;
    }

    public Employee(int employeeID, String name, String department, double salary) {
        this.employeeID = employeeID;
        this.name = name;
        this.department = department;
        this.salary = salary;
        totalEmployees++;
    }


    public double calculateSalary() {
        return this.salary;
    }

   
    public double getSalary() {
        return this.salary;
    }

   
    public void displayEmployeeInfo() {
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: " + salary);
    }

    
    public static void displayTotalEmployees() {
        System.out.println("Total Employees: " + totalEmployees);
    }

    public static void main(String[] args) {
        
        Employee emp1 = new Employee();
        
       
        Employee emp2 = new Employee(101, "Alice", "IT", 50000);
        Employee emp3 = new Employee(102, "Bob", "HR", 45000);

        
        emp1.displayEmployeeInfo();
        emp2.displayEmployeeInfo();
        emp3.displayEmployeeInfo();

      
        Employee.displayTotalEmployees();
    }
}    