package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.dto.GradeDTO;
import pl.arturzaczek.demoSchool.model.entities.Grade;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;

import java.util.Optional;

@Service
public class GradeService {

    Logger logger = LoggerFactory.getLogger(GradeService.class);
    GradeRepository gradeRepository;
    UserRepository userRepository;
    private Grade resultGrade;

    @Autowired
    public GradeService(GradeRepository gradeRepository, UserRepository userRepository) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
    }

    public void addGradeToStudentById(Long id_student, GradeDTO gradeDTO) {
        Optional<User> byId = userRepository.findById(id_student);
        User user = byId.orElseGet(() -> new User("Error", "student not found"));
        Grade grade = mapDTOtoGradeEntity(gradeDTO);
        grade.setStudent(user.getId());
        user.addToGradeList(grade);
        userRepository.save(user);
    }

    private Grade mapDTOtoGradeEntity(GradeDTO gradeDTO){
        resultGrade = new Grade(gradeDTO.getGradeValue(),gradeDTO.getSubjectName());
        return resultGrade;
    }
}
