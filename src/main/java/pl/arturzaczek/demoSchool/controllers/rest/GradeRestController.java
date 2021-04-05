package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.arturzaczek.demoSchool.model.entities.Grade;
import pl.arturzaczek.demoSchool.model.entities.Student;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;

@RestController
public class GradeRestController {

    Logger logger = LoggerFactory.getLogger(GradeRestController.class);
    StudentRepository studentRepository;
    GradeRepository gradeRepository;

    @Autowired
    public GradeRestController(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository= gradeRepository;
    }

    @PostMapping("/addGrade")
    public Student addGrade(@RequestBody(required = false) Student student){
        System.out.println("addGrade()");
        logger.debug("addGrade()");
        System.out.println("grade list");
        student.getGradeList().forEach(grade -> System.out.println(grade));
//        System.out.println("student: " + student.setGradeList());
        return student;
    }
}
