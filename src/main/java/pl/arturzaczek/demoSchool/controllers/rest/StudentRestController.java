package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.service.StudentService;
import pl.arturzaczek.demoSchool.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class StudentRestController {
    UserService userService;
    StudentService studentService;
    Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    public StudentRestController(StudentService studentService,UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @GetMapping("/students")
    public List<User> getStudents(){
        logger.debug("url= /rest/students, method=getStudents()");
        return userService.getUsersList();
    }

    @PostMapping("/student")
    public ResponseEntity addStudent(@RequestBody User user){
        logger.debug("url= /rest/student, method=addStudent() STUDENT: " + user);
        userService.saveUser(user);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/add20Students")
    public ResponseEntity add20Students(){
        userService.save20users();
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @GetMapping("/student/{student_id}")
    public ResponseEntity<User> getStudentById(@PathVariable String student_id){
        logger.debug("url= /rest/student/{student_id}, method=getStudentById() STUDENT: " + student_id);
        Long long_id = Long.parseLong(student_id);
        return userService.getStudentById(long_id);
    }

    @DeleteMapping("/student/{student_id}")
    public ResponseEntity deleteStudentById(@PathVariable String student_id){
        logger.debug("url= /rest/student/{student_id}, method=deleteStudentById() STUDENT: " + student_id);
        Long long_id = Long.parseLong(student_id);
        return userService.deleteById(long_id);
    }
}
