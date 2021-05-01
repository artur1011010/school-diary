package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.dto.GradeDTO;
import pl.arturzaczek.demoSchool.model.entities.Grade;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.GradeService;

import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class GradeRestController {

    Logger logger = LoggerFactory.getLogger(GradeRestController.class);
    UserRepository userRepository;
    GradeRepository gradeRepository;
    GradeService gradeService;

    @Autowired
    public GradeRestController(UserRepository userRepository, GradeRepository gradeRepository, GradeService gradeService) {
        this.userRepository = userRepository;
        this.gradeRepository = gradeRepository;
        this.gradeService = gradeService;
    }

    @PostMapping("/addGradeRest")
    public User addGradeRest() {
        logger.debug("url= /rest/addGradeRest, method=addGradeRest()");
        User user = new User("Artur", "Rest test");
        user.addToGradeList(new Grade(80,"Matematyka", user.getId()));
        userRepository.save(user);
        return user;
    }

    @PostMapping("/addSecondGradeRest")
    public User addSecondGradeRest() {
        logger.debug("url= /rest/addSecondGradeRest, method=addSecondGradeRest()");
        User resultUser = new User("Error", "wrong id");
        Optional<User> byId = userRepository.findById(1L);
        if (!byId.isPresent()) {
            return resultUser;
        }
        resultUser = byId.get();
        resultUser.addToGradeList(new Grade(50));
        userRepository.save(resultUser);
        resultUser.getGradeList().forEach(grade-> System.out.println("grade: " + grade));
        return resultUser;
    }

    @PostMapping("/addGrade/{student}")
    public User addGradeToStudentByIdTest(@PathVariable String student) {
        logger.debug("url= /rest/addGrade/{student}, method=addGradeToStudentByIdTest(), STUDENT: " + student);
        User resultUser = new User("Error", "wrong id");
        Long id = null;
        try {
            id = Long.parseLong(student);
        }catch (Exception exception){
            logger.error("addGradeToStudentByIdTest() parse student id error: " +  student);
            return resultUser;
        }
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            return resultUser;
        }
        resultUser = byId.get();
        resultUser.addToGradeList(new Grade(100));
        userRepository.save(resultUser);
        resultUser.getGradeList().forEach(grade-> System.out.println("grade: " + grade));
        return resultUser;
    }

    @PostMapping("/grade/{student}")
    public void addGradeToStudentById(@PathVariable String student, @RequestBody GradeDTO gradeDTO) {
        logger.debug("url= /rest/grade/{student}, method=addGradeToStudentById(), STUDENT: " + student + " , GRADE: " + gradeDTO);
        Long id = null;
        try {
            id = Long.parseLong(student);
        }catch (Exception exception){
            logger.error("addGradeToStudentById() parse student id error: " +  student);
            return;
        }
        gradeService.addGradeToStudentById(id, gradeDTO);
    }
}

