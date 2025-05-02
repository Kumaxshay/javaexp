package experiment9;

import java.io.*;

class Person implements Serializable {
	private static final long serialVersionUID = 1L;
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class SerializePerson {
    public static void main(String[] args) {
        Person person = new Person("John", 25);  // Create a new Person object

        // Serialize the object to a file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("person.txt"))) {
            out.writeObject(person);  // Write the object to the file
            System.out.println("Object serialized successfully!");

        } catch (IOException e) {
            System.out.println("Error: Could not serialize the object.");
        }

        // Deserialize the object from the file
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("person.txt"))) {
            Person deserializedPerson = (Person) in.readObject();  // Read the object from the file
            System.out.println("Object deserialized successfully!");
            System.out.println("Name: " + deserializedPerson.name + ", Age: " + deserializedPerson.age);

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: Could not deserialize the object.");
        }
    }
}
