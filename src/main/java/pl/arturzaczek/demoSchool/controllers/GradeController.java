package pl.arturzaczek.demoSchool.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeController {

    Logger logger = LoggerFactory.getLogger(GradeController.class);

    @GetMapping("/addGradeView")
    public String addGrade(){
        logger.debug("addGrade()");
        return "home";
    }
}
