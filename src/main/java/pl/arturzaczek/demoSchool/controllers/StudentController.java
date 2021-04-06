package pl.arturzaczek.demoSchool.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.arturzaczek.demoSchool.model.entities.Student;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;


import java.util.List;

@Controller
public class StudentController {

    StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @GetMapping("/addStudent")
    public String addStudent(){
        logger.debug("url= /addStudent, method=addStudent()");
        return "students/addStudent";
    }

    @GetMapping("/studentsList")
    public String getStudentsList(){
        logger.debug("url= /studentsList, method=getStudentsList()");
        return "students/studentsList";
    }
//    @GetMapping("/student/{id}")
//    public String getStudent(){
//        logger.debug("url= //student/{id}, method=getStudent()");
//        return "students/student";
//    }
}
