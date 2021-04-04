package pl.arturzaczek.demoSchool.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping(value = {"/","/home","/index"})
    public String getHomePage(){
        logger.debug("getHomePage()");
        return "home";
    }
    @GetMapping("/test")
    public String getTestPage(){
        logger.debug("getTestPage()");
        return "test";
    }
    @GetMapping("/test2")
    public String getTest2Page(){
        logger.debug("getTestPage()");
        return "test2";
    }
}
