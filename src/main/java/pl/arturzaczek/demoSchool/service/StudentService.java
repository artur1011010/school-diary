package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;
import pl.arturzaczek.demoSchool.model.repositories.SubjectRepository;


@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    GradeRepository gradeRepository;
    StudentRepository studentRepository;
    SubjectRepository subjectRepository;

    @Autowired
    public StudentService(GradeRepository gradeRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }
//    List.of
}
