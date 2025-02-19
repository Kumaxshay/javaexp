class Course {
   
    String courseName;
    String courseCode;

    
    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

   
    void displayCourseDetails() {
        System.out.println("Course Name: " + courseName);
        System.out.println("Course Code: " + courseCode);
    }

    public static void main(String[] args) {
        
        Course course1 = new Course("Java Programming", "CS101");
        
        course1.displayCourseDetails();
    }
}