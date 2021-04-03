package pl.arturzaczek.demoSchool.model.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.arturzaczek.demoSchool.controllers.rest.StudentRestController;

import javax.persistence.*;

@Entity
@Table(name = "GRADE")
public class Grade extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
    @OneToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    private Integer gradeValue;

    public Grade() {
    }

    public Grade(Professor professor, Subject subject) {
        this.professor = professor;
        this.subject = subject;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        if (gradeValue > 100 || gradeValue < 0) {
            Logger logger = LoggerFactory.getLogger(StudentRestController.class);
            logger.error("setGradeValue(), wrong value ", gradeValue);
            return;
        }
        this.gradeValue = gradeValue;
    }

    @Override
    public String toString() {
        return "Grade: " +
                "professor: " + professor +
                ", subject: " + subject +
                ", gradeValue: " + gradeValue +
                "id: " + super.id;
    }
}
