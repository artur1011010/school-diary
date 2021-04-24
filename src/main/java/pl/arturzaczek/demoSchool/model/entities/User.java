package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @OneToMany(mappedBy = "student")
    private List<Grade> gradeList;

    public User() {
    }

    public User(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = new Date(System.currentTimeMillis());
    }

    public User(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        super.addedDate = LocalDateTime.now();
    }

    public User(String firstName, String lastName, Date birthDate, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        super.addedDate = LocalDateTime.now();
        this.birthDate = birthDate;
        this.email = email;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public void addToGradeList(Grade grade) {
        if (gradeList == null) {
            gradeList = new ArrayList<Grade>();
        }
        gradeList.add(grade);
    }


    public LocalDateTime getAddedDate() {
        return super.addedDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", addedDate=" + addedDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", gradeList=" + gradeList +
                '}';
    }
}
