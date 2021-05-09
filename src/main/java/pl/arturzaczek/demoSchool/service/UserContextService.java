package pl.arturzaczek.demoSchool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;

@Service
public class UserContextService {

    private UserRepository userRepository;

    @Autowired
    public UserContextService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public String getLoggedAs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }

    public Long getLoggedId() {
//        System.out.println("getLoggedId");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
//        Todo clean up
//        System.out.println("authentication.getName() " + authentication.getName());
        Long id = userRepository.findFirstByEmail(authentication.getName()).get().getId();
        return id;
    }

    public boolean hasRole(String roleName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(s -> s.equals(roleName));
    }

    public boolean isLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
