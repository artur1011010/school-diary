package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SUBJECT")
public class Subject extends BaseEntity {
    @Column(name = "subject_name")
    private String subjectName;

    public Subject() {
    }
    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Subject:" +
                "id: " + id +
                ", addedDate: " + addedDate +
                ", subjectName: " + subjectName;
    }
}
