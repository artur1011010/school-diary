package pl.arturzaczek.demoSchool.model.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.arturzaczek.demoSchool.controllers.rest.StudentRestController;

import javax.persistence.*;

@Entity
@Table(name = "GRADE")
public class Grade extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "id_professor")
    private Professor professor;
    @OneToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;
    @ManyToOne
    @JoinColumn(name = "id_student")
    private Student student;
    private Integer gradeValue;


    public Grade() {
    }
    public Grade(Integer gradeValue) {
        setGradeValue(gradeValue);
    }
    public Grade(Professor professor, Subject subject, Integer gradeValue) {
        this.professor = professor;
        this.subject = subject;
        setGradeValue(gradeValue);
    }

    public Grade(Professor professor, Subject subject, Student student, Integer gradeValue) {
        this.professor = professor;
        this.subject = subject;
        this.student = student;
        setGradeValue(gradeValue);
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getGradeValue() {
        return this.gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        if (gradeValue > 100 || gradeValue < 0) {
            Logger logger = LoggerFactory.getLogger(StudentRestController.class);
            logger.error("setGradeValue(), wrong value " +  gradeValue + ", value set on 0");
            this.gradeValue = 0;
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
