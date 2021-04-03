package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "MARKS")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long mark_id;
    Long student_id;
    Long professor_id;
    Long subject_id;

}
