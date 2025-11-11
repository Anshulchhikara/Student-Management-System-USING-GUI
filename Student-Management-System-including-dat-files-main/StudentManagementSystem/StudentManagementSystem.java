import java.io.*;
import java.util.*;

public class StudentManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "students.dat";

    public static void main(String[] args) {
        loadStudents();

        int choice;

        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Roll No");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 4);
    }

    static void addStudent() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter roll number: ");
        String roll = sc.nextLine();

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        Student s = new Student(name, roll, course);
        students.add(s);
        saveStudents();
        System.out.println("✅ Student added successfully!");
    }

    static void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No student records found.");
        } else {
            for (Student s : students) {
                s.display();
            }
        }
    }

    static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        String roll = sc.nextLine();

        boolean found = false;

        for (Student s : students) {
            if (s.rollNo.equalsIgnoreCase(roll)) {
                s.display();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("❌ Student with Roll No " + roll + " not found.");
        }
    }

    static void saveStudents() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(students);
        } catch (IOException e) {
            System.out.println("⚠️ Error saving students: " + e.getMessage());
        }
    }

    static void loadStudents() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                students = (ArrayList<Student>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("⚠️ Error loading students: " + e.getMessage());
            }
        }
    }
}
