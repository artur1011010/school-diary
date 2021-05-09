package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.entities.Role;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.RoleRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;

@Service
public class RoleService {

    Logger logger = LoggerFactory.getLogger(RoleService.class);

    RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
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

}
