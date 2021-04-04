package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.arturzaczek.demoSchool.model.entities.Student;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;

import java.util.ArrayList;
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
        logger.debug("getStudents()");
        List<Student> studentList = studentRepository.findAll();
        logger.debug("getStudents()", studentList);
        return studentList;
    }

    @PostMapping("/addStudent")
    public void addStudent(@RequestBody Student student){
        System.out.println("/addStudent " + student);
        logger.debug("addStudent() rest", student.toString());
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
        logger.debug("add5Students() rest",studentlist );
        studentRepository.saveAll(studentlist);
    }
}
