package pl.arturzaczek.demoSchool.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;

@Controller
public class StudentController {

    StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/addStudent")
    public String addStudent() {
        logger.debug("url= /addStudent, method=addStudent()");
        return "student/addStudent";
    }

    @GetMapping("/studentsList")
    public String getStudentsList() {
        logger.debug("url= /studentsList, method=getStudentsList()");
        return "student/studentsList";
    }

    @GetMapping("/student")
    public String getStudentProfile() {
        return "student";
    }

    @GetMapping("/test1")
    public String getTest1() {
        return "test1";
    }

}
