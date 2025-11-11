import java.io.Serializable;

class Student implements Serializable {
    String name;
    String rollNo;
    String course;

    Student(String name, String rollNo, String course) {
        this.name = name;
        this.rollNo = rollNo;
        this.course = course;
    }

    void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name   : " + name);
        System.out.println("Course : " + course);
        System.out.println("-----------------------");
    }
}
