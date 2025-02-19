class University {

    static String universityName = "UPES University";
    

    String studentName;

    
    public University(String studentName) {
        this.studentName = studentName;
    }

    static void displayUniversityName() {
        System.out.println("University Name: " + universityName);
    }

    
    void displayStudent() {
        System.out.println("Student Name: " + studentName);
    }

    public static void main(String[] args) {

        University.displayUniversityName();

    
        University student1 = new University("Raju");
        University student2 = new University("Ravi");

       
        student1.displayStudent();
        student2.displayStudent();
    }
}
