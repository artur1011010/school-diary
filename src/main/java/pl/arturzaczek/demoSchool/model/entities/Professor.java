package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "PROFESSOR")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long professor_id;


}
