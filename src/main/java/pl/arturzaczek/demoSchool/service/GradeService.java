package pl.arturzaczek.demoSchool.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.dto.GradeDTO;
import pl.arturzaczek.demoSchool.model.entities.Grade;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;
import pl.arturzaczek.demoSchool.utils.GradeMapper;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GradeService {

    private final UserRepository userRepository;
    private final GradeMapper gradeMapper;

    public void addGradeToStudentById(final Long studentId, final GradeDTO gradeDTO) {
        log.info("addGradeToStudentById: \n{}", gradeDTO);
        Optional<User> byId = userRepository.findById(studentId);
        User user = byId.orElseGet(() -> User.builder().firstName("Error").build());
        Grade grade = gradeMapper.mapDTOtoGradeEntity(gradeDTO);
        grade.setStudent(user.getId());
        user.addToGradeList(grade);
        userRepository.save(user);
    }

}
