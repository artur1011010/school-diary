package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.dto.UserRegisterForm;
import pl.arturzaczek.demoSchool.model.entities.Role;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.RoleRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    GradeRepository gradeRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;
    StudentService studentService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(GradeRepository gradeRepository, UserRepository userRepository, StudentService studentService, PasswordEncoder passwordEncoder,RoleRepository roleRepository) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
        this.studentService = studentService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public void registerUser (UserRegisterForm userRegisterForm){
        User user = new User();
        user.setAddedDate(LocalDateTime.now());
        user.setFirstName(userRegisterForm.getFormName());
        user.setLastName(userRegisterForm.getFormLastName());
        user.setEmail(userRegisterForm.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userRegisterForm.getPassword()));
        getORCreateDefaultRole(user);
        userRepository.save(user);
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

    public boolean checkIfUserExist (String email){
        Optional<User> userOptional = userRepository.findFirstByEmail(email);
        if(userOptional.isPresent()){
            return true;
        }
        return false;
    }
    protected void getORCreateDefaultRole(User user) {
        Role role = roleRepository.findByRoleName(RoleEnum.ROLE_USER.toString())
                .orElseGet(() -> roleRepository.save(new Role(RoleEnum.ROLE_USER.toString())));
        user.addRole(role);
    }
    protected void getORCreateDefaultRole(User user, RoleEnum roleEnum) {
        Role role = roleRepository.findByRoleName(roleEnum.toString())
                .orElseGet(() -> roleRepository.save(new Role(roleEnum.toString())));
        user.addRole(role);
    }

    public List<User> getUserList(){
        List<User> users = userRepository.findAll();
        return users;
    }
}
