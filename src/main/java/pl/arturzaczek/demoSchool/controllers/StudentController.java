package pl.arturzaczek.demoSchool.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;

@Controller
public class StudentController {

    UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    @GetMapping("/studentProfile")
    public String getStudentProfile() {
        return "studentProfile";
    }

    @GetMapping("/studentProfile/{id}")
    public String getStudentProfile2(@PathVariable String id, Model model) {
        model.addAttribute("student_id", id);
        return "studentProfile";
    }
}
