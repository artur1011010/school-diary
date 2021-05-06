package pl.arturzaczek.demoSchool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.arturzaczek.demoSchool.model.dto.UserRegisterForm;
import pl.arturzaczek.demoSchool.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String getLoginForm() {
        return "user/login-form";
    }

    @GetMapping("user/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "user/register-form";
    }

    @PostMapping("/user/register/new")
    public String registerUser(@ModelAttribute UserRegisterForm userRegisterForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRegisterForm", userRegisterForm);
            return "user/register-form";
        }
        if(userService.checkIfUserExist(userRegisterForm.getEmail())){
            model.addAttribute("employeeRegisterForm", userRegisterForm);
            model.addAttribute("user_error","user exist");
            return "user/register-form";
        }
        userService.registerUser(userRegisterForm);
        model.addAttribute("registered_success","new user registered successfully");
        System.out.println("user registered successfully");
        return "redirect:/index";
    }

    @GetMapping("/user/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/user/login-form";
    }
//  todo deprecated

//    @GetMapping("/user/loggedIn")
//    public String loggedIn (){
//        return "user/loggedIn";
//    }

    @GetMapping("/user/login-form")
    public String userLogin () {
        return "user/login-form";
    }
    @GetMapping("user/loginError")
    public String userLoginError () {
        return "user/loginError";
    }
}
