public class student {
        String name;
        int age;
    
        public student() {
            name = "Mayank Thakur";
            age = 18;
        }
    
        public student(String n, int a) {
            name = n;
            age = a;
        }
    
        public void display() {
            System.out.println(name + " " + age);
        }
    
        public static void main(String[] args) {
            student s1 = new student();
            s1.display();
    
            student s2 = new student("John", 20);
            s2.display();
        }
    }

