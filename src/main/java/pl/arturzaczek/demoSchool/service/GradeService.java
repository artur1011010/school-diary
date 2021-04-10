package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.dto.GradeDTO;
import pl.arturzaczek.demoSchool.model.entities.Grade;
import pl.arturzaczek.demoSchool.model.entities.Student;
import pl.arturzaczek.demoSchool.model.entities.Subject;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;
import pl.arturzaczek.demoSchool.model.repositories.SubjectRepository;
import java.util.Optional;

@Service
public class GradeService {

    Logger logger = LoggerFactory.getLogger(GradeService.class);
    GradeRepository gradeRepository;
    StudentRepository studentRepository;
    SubjectRepository subjectRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    public Boolean addGradeToStudentById(Long id_student, GradeDTO gradeDTO) {
        logger.debug("url= /rest/grade/{student}, method=addGradeToStudentById(), id_student: " + id_student + ",  gradeDTO: " + gradeDTO);
        Optional<Student> byId1 = studentRepository.findById(id_student);

//      TODO  - zmienic metode tak zeby zwracala kod bledu w przypadku braku studenta
        Student student = byId1.orElseGet(Student::new);
        Optional<Subject> bySubjectName = subjectRepository.findBySubjectName(gradeDTO.getSubjectName());
        Subject subject = bySubjectName.orElseGet(() -> new Subject(gradeDTO.getSubjectName()));
        subjectRepository.save(subject);
        Grade grade = new Grade(subject, student, gradeDTO.getGradeValue());
        gradeRepository.save(grade);
        student.addToGradeList(grade);
        studentRepository.save(student);
        return true;
    }
}
