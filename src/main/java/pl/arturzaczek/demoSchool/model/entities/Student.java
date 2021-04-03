package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long student_id;
    String name;
    String last_name;

    public Student() {
    }

    public Student(String name, String last_name) {
        this.name = name;
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        return "Student: " +
                "id:" + student_id +
                ", name: " + name +
                ", last name:" + last_name;
    }
}
