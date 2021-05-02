package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<User> getStudentById(Long student_id){
        Optional<User> byId = userRepository.findById(student_id);
        if(byId.isEmpty()){
            logger.warn("student not found, id: " + student_id);
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(byId.get(), HttpStatus.OK);
    }

    public ResponseEntity deleteById(Long long_id){
        Optional<User> byId = userRepository.findById(long_id);
        if(byId.isEmpty()){
            logger.warn("deleteStudentById() rest " + long_id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(long_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
