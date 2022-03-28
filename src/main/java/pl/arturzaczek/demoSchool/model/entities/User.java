package pl.arturzaczek.demoSchool.model.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "USER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @ManyToMany
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Grade> gradeList;
    @Column(name = "password_hash", length = 100)
    private String passwordHash;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public void addRole(Role role) {
        if (roleSet == null) {
            roleSet = new HashSet<>();
        }
        roleSet.add(role);
    }

    public void addToGradeList(Grade grade) {
        if (gradeList == null) {
            gradeList = new ArrayList<Grade>();
        }
        gradeList.add(grade);
    }
}
