package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "PROFESSOR")
public class Professor extends BaseEntity{

    private String firstName;
    private String lastName;

    public Professor() {

    }
}
