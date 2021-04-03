package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long student_id;
    String firstName;
    String lastName;

    public Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student: " +
                "id:" + student_id +
                ", name: " + firstName +
                ", last name:" + lastName;
    }
}
