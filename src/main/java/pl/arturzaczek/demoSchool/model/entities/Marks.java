package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.*;

@Entity
/**
 * zmienilbym to na "Rating" aldo "Grade" bo mozna podawac wartosci w % np: 60% jako ocene
 * bo z marks to ja nie wiem czy mam oceny ABCD.. czy 1..6 i ktora jest wyzsza.
 */
@Table(name = "MARKS")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long mark_id;
    Long student_id;
    Long professor_id;
    Long subject_id;

}
