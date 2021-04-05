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

/**    /students TODO zmiana endpointow zgodnie z konwencja
 *
A resource can be a singleton or a collection. For example, “customers” is a
    collection resource and “customer” is a singleton resource (in a banking domain).
    We can identify “customers” collection resource using the URI “/customers”.
    We can identify a single “customer” resource using the URI “/customers/{customerId}”.

    A resource may contain sub-collection resources also. For example,
    sub-collection resource “accounts” of a particular “customer” can be identified using the
    URN “/customers/{customerId}/accounts” (in a banking domain). Similarly, a singleton resource
    “account” inside the sub-collection resource “accounts” can be identified
    as follows: “/customers/{customerId}/accounts/{accountId}”.
*/


    @GetMapping("/students")
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
    @GetMapping("/add1StudentWithGrade")
    public void add1StudentWithGrade(){
        Student student = new Student("Artur", "Z ocenami");
        System.out.println("student " + student);
        logger.debug("add1StudentWithGrade() rest " + student.toString());
        student.getGradeList().add(new Grade(80));
        System.out.println("student " + student);
        studentRepository.save(student);
    }
}
