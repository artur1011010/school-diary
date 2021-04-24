package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class StudentRestController {
    UserRepository userRepository;
    StudentService studentService;
    Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    public StudentRestController(UserRepository userRepository, StudentService studentService) {
        this.userRepository = userRepository;
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<User> getStudents(){
        logger.debug("url= /rest/students, method=getStudents()");
        return userRepository.findAll();
    }

    @PostMapping("/student")
    public void addStudent(@RequestBody User user){
        logger.debug("url= /rest/student, method=addStudent() STUDENT: " + user);
        userRepository.save(user);
    }

    @GetMapping("/add5Students")
    public void add5Students(){
        List userList = new ArrayList<User>();
        userList.add(new User("Artur", "Aaaaa"));
        userList.add(new User("Marcin", "Bbbbb"));
        userList.add(new User("Mariusz", "Cccc"));
        userList.add(new User("Anna", "Dddd"));
        userList.add(new User("Kamila", "Eeeee"));
        logger.debug("url= /rest/add5Students, method=add5Students() STUDENT LIST: " + userList);
        userList.forEach(stu -> System.out.println(stu.toString()));
        userRepository.saveAll(userList);
    }

    @GetMapping("/student/{student_id}")
    public User getStudentById(@PathVariable String student_id){
        logger.debug("url= /rest/student/{student_id}, method=getStudentById() STUDENT: " + student_id);
        Long long_id = Long.parseLong(student_id+"");
        Optional<User> byId = userRepository.findById(long_id);
        if(!byId.isPresent()){
            logger.warn("deleteStudentById() rest " + student_id);
            return null;
        }
        User user = byId.get();
        System.out.println(user.getGradeList());
        return user;
    }

    @DeleteMapping("/student/{student_id}")
    public String deleteStudentById(@PathVariable String student_id){
        logger.debug("url= /rest/student/{student_id}, method=deleteStudentById() STUDENT: " + student_id);
        Long long_id = Long.parseLong(student_id+"");
        Optional<User> byId = userRepository.findById(long_id);
        if(!byId.isPresent()){
            logger.warn("deleteStudentById() rest " + student_id);
            return "not found: " + student_id;
        }
        userRepository.deleteById(long_id);
        return "student deleted + " + long_id;
    }
}
