package pl.arturzaczek.demoSchool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping(value = {"/","/home","/index"})
    public String getHome(){
        return "home";
    }

}
