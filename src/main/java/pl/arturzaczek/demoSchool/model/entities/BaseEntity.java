package pl.arturzaczek.demoSchool.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Column (name = "added_date")
    protected LocalDateTime addedDate;

}
