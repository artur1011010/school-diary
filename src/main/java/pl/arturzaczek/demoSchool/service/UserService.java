package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.entities.Grade;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    GradeRepository gradeRepository;
    StudentService studentService;
    UserRepository userRepository;

    @Autowired
    public UserService(GradeRepository gradeRepository, UserRepository userRepository, StudentService studentService) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
        this.studentService = studentService;
    }

    public List<User> getUsersList(){
        return userRepository.findAll();
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public void save20users(){
        List<User> randomUserM = studentService.createRandomUserM();
        List<User> randomUserF = studentService.createRandomUserF();
        randomUserM.addAll(randomUserF);
        userRepository.saveAll(randomUserM);
    }
}
