package pl.arturzaczek.demoSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;

@Service
public class GradeService {

    GradeRepository gradeRepository;
    StudentRepository studentRepository;

    @Autowired
    public GradeService (GradeRepository gradeRepository, StudentRepository studentRepository){
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
    }
}
