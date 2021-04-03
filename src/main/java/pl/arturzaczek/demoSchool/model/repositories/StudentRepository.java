package pl.arturzaczek.demoSchool.model.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arturzaczek.demoSchool.model.entities.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
