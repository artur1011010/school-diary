package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.arturzaczek.demoSchool.model.entities.Student;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;

import java.util.List;

@RestController
public class StudentRestController {
    StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/getStudents")
    public List<Student> getStudents(){
        logger.debug("logger debug - getStudents()");
        List<Student> studentList = studentRepository.findAll();
        logger.debug("logger debug - getStudents()", studentList);
        return studentList;
    }
}
