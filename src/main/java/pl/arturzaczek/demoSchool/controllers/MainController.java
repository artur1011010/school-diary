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
        logger.debug("url= {\"/\",\"/home\",\"/index\"}, method=getHomePage()");
        return "home";
    }
    @GetMapping("/test1")
    public String getTestPage(){
        logger.debug("url= /test, method=getTestPage()");
        return "test1";
    }
}
