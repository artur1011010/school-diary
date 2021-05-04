package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "USER")
public class User extends BaseEntity {

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet;
    @Column(name = "first_name", length = 150)
    private String firstName;
    @Column(name = "last_name", length = 100)
    private String lastName;
    @Column(unique = true)
    private String email;
    @Basic
    private LocalDate birthDate;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Grade> gradeList;
    @Column(name = "password_hash", length = 100)
    private String passwordHash;

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public User() {
    }

    public User(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, LocalDate birthDate) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public User(String firstName, String lastName, LocalDate birthDate, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
    }
    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void addRole(Role role) {
        if (roleSet == null) {
            roleSet = new HashSet<>();
        }
        roleSet.add(role);
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
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
