package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.entities.Student;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class StudentRestController {
    StudentRepository studentRepository;
    Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    public StudentRestController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        logger.debug("url= /rest/students, method=getStudents()");
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    @PostMapping("/student")
    public void addStudent(@RequestBody Student student){
        logger.debug("url= /rest/student, method=addStudent() STUDENT: " + student);
        studentRepository.save(student);
    }

    @GetMapping("/add5Students")
    public void add5Students(){
        List studentlist = new ArrayList<Student>();
        studentlist.add(new Student("Artur", "Aaaaa"));
        studentlist.add(new Student("Marcin", "Bbbbb"));
        studentlist.add(new Student("Mariusz", "Cccc"));
        studentlist.add(new Student("Anna", "Dddd"));
        studentlist.add(new Student("Kamila", "Eeeee"));
        logger.debug("url= /rest/add5Students, method=add5Students() STUDENT LIST: " + studentlist);
        studentRepository.saveAll(studentlist);
    }
    @GetMapping("/student/{student_id}")
    public Student getStudentById(@PathVariable String student_id){
        logger.debug("url= /rest/student/{student_id}, method=getStudentById() STUDENT: " + student_id);
        Long long_id = Long.parseLong(student_id+"");
        Optional<Student> byId = studentRepository.findById(long_id);
        if(!byId.isPresent()){
            logger.warn("deleteStudentById() rest " + student_id);
            return null;
        }
        Student student = byId.get();
        return student;
    }

    @DeleteMapping("/student/{student_id}")
    public String deleteStudentById(@PathVariable String student_id){
        logger.debug("url= /rest/student/{student_id}, method=deleteStudentById() STUDENT: " + student_id);
        Long long_id = Long.parseLong(student_id+"");
        Optional<Student> byId = studentRepository.findById(long_id);
        if(!byId.isPresent()){
            logger.warn("deleteStudentById() rest " + student_id);
            return "not found: " + student_id;
        }
        studentRepository.deleteById(long_id);
        return "student deleted + " + long_id;
    }
}
