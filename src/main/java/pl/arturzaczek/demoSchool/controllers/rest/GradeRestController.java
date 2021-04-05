package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.entities.Grade;
import pl.arturzaczek.demoSchool.model.entities.Student;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;

import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class GradeRestController {

    Logger logger = LoggerFactory.getLogger(GradeRestController.class);
    StudentRepository studentRepository;
    GradeRepository gradeRepository;

    @Autowired
    public GradeRestController(StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
    }

//    @PostMapping("/addGrade")
//    public Student addGrade(@RequestBody(required = false) Student student) {
//        System.out.println("addGrade()");
//        logger.debug("addGrade()");
//        System.out.println("grade list");
//        student.getGradeList().forEach(grade -> System.out.println(grade));
////        System.out.println("student: " + student.setGradeList());
//        return student;
//    }

    @PostMapping("/addGradeRest")
    public Student addGradeRest() {
        System.out.println("addGradeRest()");
        logger.debug("addGradeRest()");
        System.out.println("grade list");

        logger.debug("logger debug - addGradeRest()");
        Student student = new Student("Artur", "Rest test");
        student.addToGradeList(new Grade(80));
        studentRepository.save(student);
        return student;
    }

    @PostMapping("/addSecondGradeRest")
    public Student addSecondGradeRest() {
        System.out.println("addSecondGradeRest()");
        logger.debug("addSecondGradeRest()");
        System.out.println("grade list");

        Student resultStudent = new Student("Error", "wrong id");
        Optional<Student> byId = studentRepository.findById(1L);
        if (!byId.isPresent()) {
            return resultStudent;
        }
        //collection eager fetch
        resultStudent = byId.get();
        resultStudent.addToGradeList(new Grade(70));
        resultStudent.addToGradeList(new Grade(60));
        resultStudent.addToGradeList(new Grade(50));
        studentRepository.save(resultStudent);
        resultStudent.getGradeList().forEach(grade-> System.out.println("grade: " + grade));
        return resultStudent;
    }
    @PostMapping("/addGrade/{student}")
    public Student addGradeToStudentById( @PathVariable String student) {
        System.out.println("addGradeToStudentById()");
        logger.debug("addGradeToStudentById()");
        Student resultStudent = new Student("Error", "wrong id");
        //TODO zabezpieczyc gdzies to parsowanie ze stringa
        Long id = Long.parseLong(student);
        Optional<Student> byId = studentRepository.findById(id);
        if (!byId.isPresent()) {
            return resultStudent;
        }
        //TODO collection eager fetch
        resultStudent = byId.get();
        resultStudent.addToGradeList(new Grade(100));
        studentRepository.save(resultStudent);
        resultStudent.getGradeList().forEach(grade-> System.out.println("grade: " + grade));
        return resultStudent;
    }
}

