package pl.arturzaczek.demoSchool.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.arturzaczek.demoSchool.model.entities.Subject;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findBySubjectName(String subjectName);
}
