package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.model.dto.GradeDTO;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;
import pl.arturzaczek.demoSchool.service.GradeService;

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

    @PostMapping("/grade/{student}")
    public ResponseEntity addGradeToStudentById(@PathVariable String student, @RequestBody GradeDTO gradeDTO) {
        logger.debug("url= /rest/grade/{student}, method=addGradeToStudentById(), STUDENT: " + student + " , GRADE: " + gradeDTO);
        Long id = null;
        try {
            id = Long.parseLong(student);
        }catch (Exception exception){
            logger.error("addGradeToStudentById() parse student id error: " +  student);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        gradeService.addGradeToStudentById(id, gradeDTO);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}

