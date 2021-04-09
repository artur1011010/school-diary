package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.entities.Grade;
import pl.arturzaczek.demoSchool.model.entities.Student;
import pl.arturzaczek.demoSchool.model.entities.Subject;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;

import java.time.LocalDateTime;
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

    private Student getStudent(Long id) {
        Student resultStudent = null;
        Optional<Student> byId = studentRepository.findById(id);
        if (!byId.isPresent()) {
            return resultStudent;
        }
        resultStudent = byId.get();
        return resultStudent;
    }

    @PostMapping("/addGradeRest")
    public Student addGradeRest() {
        logger.debug("url= /rest/addGradeRest, method=addGradeRest()");
        Student student = new Student("Artur", "Rest test");
        student.addToGradeList(new Grade(80));
        studentRepository.save(student);
        return student;
    }

    @PostMapping("/addSecondGradeRest")
    public Student addSecondGradeRest() {
        logger.debug("url= /rest/addSecondGradeRest, method=addSecondGradeRest()");
        Student resultStudent = new Student("Error", "wrong id");
        Optional<Student> byId = studentRepository.findById(1L);
        if (!byId.isPresent()) {
            return resultStudent;
        }
        //TODO collection eager fetch
        resultStudent = byId.get();
        resultStudent.addToGradeList(new Grade(50));
        studentRepository.save(resultStudent);
        resultStudent.getGradeList().forEach(grade-> System.out.println("grade: " + grade));
        return resultStudent;
    }

    @PostMapping("/addGrade/{student}")
    public Student addGradeToStudentByIdTest(@PathVariable String student) {
        logger.debug("url= /rest/addGrade/{student}, method=addGradeToStudentByIdTest(), STUDENT: " + student);
        Student resultStudent = new Student("Error", "wrong id");
        Long id = null;
        try {
            id = Long.parseLong(student);
        }catch (Exception exception){
            logger.error("addGradeToStudentByIdTest() parse student id error: " +  student);
            return resultStudent;
        }
        Optional<Student> byId = studentRepository.findById(id);
        if (!byId.isPresent()) {
            return resultStudent;
        }
        resultStudent = byId.get();
        resultStudent.addToGradeList(new Grade(100));
        studentRepository.save(resultStudent);
        resultStudent.getGradeList().forEach(grade-> System.out.println("grade: " + grade));
        return resultStudent;
    }

    @PostMapping("/grade/{student}")
    public Student addGradeToStudentById(@PathVariable String student, @RequestBody Grade grade) {
        logger.debug("url= /rest/grade/{student}, method=addGradeToStudentById(), STUDENT: " + student + " , GRADE: " + grade);
        System.out.println("+++++++++++++++++++++++++++++ +++++++++++++++++++++++++");
        System.out.println("student: " + student );
        System.out.println("grade" + grade);
        System.out.println("+++++++++++++++++++++++++++++ +++++++++++++++++++++++++");
        grade.setSubject(new Subject("Maths"));
        Student resultStudent = new Student("Error", "wrong id");
        Long id = null;
        try {
            id = Long.parseLong(student);
        }catch (Exception exception){
            logger.error("addGradeToStudentById() parse student id error: " +  student);
            return resultStudent;
        }
        Optional<Student> byId = studentRepository.findById(id);
        if (!byId.isPresent()) {
            return resultStudent;
        }
        grade.setAddedDate(LocalDateTime.now());
        resultStudent = byId.get();
        resultStudent.getGradeList().add(grade);
        studentRepository.save(resultStudent);
        return resultStudent;
    }
}

