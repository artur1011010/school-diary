package pl.arturzaczek.demoSchool.controllers.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.arturzaczek.demoSchool.service.StudentService;

@RestController
@RequestMapping("/rest")
public class UserRestController {

    StudentService studentService;
    Logger logger = LoggerFactory.getLogger(UserRestController.class);

    public UserRestController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/admin/")
    public ResponseEntity createAdmin() {
        logger.debug("createAdmin()");
        studentService.initializeAdminTestUser();
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
