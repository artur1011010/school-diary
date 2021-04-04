package pl.arturzaczek.demoSchool.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arturzaczek.demoSchool.model.entities.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
