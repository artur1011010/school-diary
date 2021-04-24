package pl.arturzaczek.demoSchool.model.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.arturzaczek.demoSchool.controllers.rest.StudentRestController;

import javax.persistence.*;

@Entity
@Table(name = "GRADE")
public class Grade extends BaseEntity {

    private String subject;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
    private Long userId;
    private Integer gradeValue;

    public Grade() {
    }

    public Grade(Integer gradeValue) {
        setGradeValue(gradeValue);
    }

    public Grade(Integer gradeValue, String subject) {
        setGradeValue(gradeValue);
        this.subject = subject;
    }

    public Grade(Integer gradeValue, String subject, Long userId) {
        setGradeValue(gradeValue);
        this.subject = subject;
        this.userId = userId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getStudent() {
        return userId;
    }

    public void setStudent(Long userId) {
        this.userId = userId;
    }

    public Integer getGradeValue() {
        return this.gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        if (gradeValue > 100 || gradeValue < 0) {
            Logger logger = LoggerFactory.getLogger(StudentRestController.class);
            logger.error("setGradeValue(), wrong value " + gradeValue + ", value set on 0");
            this.gradeValue = 0;
            return;
        }
        this.gradeValue = gradeValue;
    }

    @Override
    public String toString() {
        return "Grade: " +
                ", subject: " + subject +
                ", gradeValue: " + gradeValue +
                "id: " + super.id;
    }
}
