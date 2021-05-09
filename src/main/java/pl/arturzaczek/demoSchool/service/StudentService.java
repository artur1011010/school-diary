package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.entities.User;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    GradeRepository gradeRepository;
    UserRepository userRepository;
    RoleService roleService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(GradeRepository gradeRepository, UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    List<String> studentNamesM = new ArrayList<>(
            Set.of("Artur", "Michał", "Marcin", "Mateusz", "Krzysztof", "Piotr", "Paweł", "Adrian", "Kamil", "Sebastian", "Przemysław", "Dawid", "Karol", "Tomasz", "Wojciech"));
    List<String> studentNamesF = new ArrayList<>(
            Set.of("Anna", "Dorota", "Katarzyna", "Justyna", "Beata", "Julia", "Marta", "Natalia", "Kamila", "Małgorzata", "Karolina", "Klaudia", "Magdalena", "Ewa"));
    List<String> studentLastNamesM = new ArrayList<>(
            Set.of("Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Grabowski",
                    "Lewandowski", "Zieliński", "Szymański", "Woźniak", "Kozłowski", "Mazur", "Krawczyk", "Kaczmarek", "Pawłowski", "Zając", "Baran", "Borkowski"));
    List<String> studentLastNamesF = new ArrayList<>(
            Set.of("Nowak", "Kowalska", "Wiśniewska", "Kowalczyk", "Kamińska", "Lewandowska", "Zielińska", "Sikora", "Pawlak", "Adamczyk", "Dudek", "Jaworska",
                    "Szymańska", "Woźniak", "Dąbrowska", "Krawczyk", "Olszewska"));
    List<String> emails = new ArrayList<>(
            List.of("@tlen.pl", "@gmail.com", "@onet.pl", "@outlook.com", "@AOL.com", "@gmail.com"));

    public void initializeAdminTestUser(){
        logger.debug("initializeAdminTestUsers()");
        Optional<User> admin1 = userRepository.findFirstByEmail("admin");
        if(admin1.isPresent()) return;
        User admin = new User("Admin", "Admin", "admin@gmail.com");
        admin.setPasswordHash(passwordEncoder.encode("admin"));
        roleService.getORCreateDefaultRole(admin, RoleEnum.ROLE_USER);
        roleService.getORCreateDefaultRole(admin, RoleEnum.ROLE_ADMIN);
        userRepository.save(admin);
    }

// TODO - save user in method instead of returning to userService
    public List<User> createRandomUserM() {
        logger.debug("createRandomUserM()");
        Collections.shuffle(studentNamesM);
        Collections.shuffle(studentLastNamesM);
        Collections.shuffle(emails);
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setFirstName(studentNamesM.get(i));
            user.setLastName(studentLastNamesM.get(i));
            String simplifiedEmail = simplifyLatinChars(studentNamesM.get(i) + "." + studentLastNamesM.get(i)) + random.nextInt(1000) + emails.get(i / 2);
            user.setEmail(simplifiedEmail);
            user.setBirthDate(between());
            user.setPasswordHash(passwordEncoder.encode(simplifiedEmail));
            roleService.getORCreateDefaultRole(user, RoleEnum.ROLE_USER);
            users.add(user);
        }
        return users;
    }

    public List<User> createRandomUserF() {
        logger.debug("createRandomUserF()");
        Collections.shuffle(studentNamesF);
        Collections.shuffle(studentLastNamesF);
        Collections.shuffle(emails);
        List<User> users = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setFirstName(studentNamesF.get(i));
            user.setLastName(studentLastNamesF.get(i));
            String simplifiedEmail = simplifyLatinChars(studentNamesF.get(i) + "." + studentLastNamesF.get(i)) + random.nextInt(1000) + emails.get(i / 2);
            user.setEmail(simplifiedEmail);
            user.setBirthDate(between());
            user.setPasswordHash(passwordEncoder.encode(simplifiedEmail));
            roleService.getORCreateDefaultRole(user, RoleEnum.ROLE_USER);
            users.add(user);
        }
        return users;
    }

    private LocalDate between() {
        LocalDate startInclusive = LocalDate.of(1970, 1, 1);
        LocalDate endExclusive = LocalDate.of(2005, 12, 30);
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    private String simplifyLatinChars(String inputString) {
        char[] input = inputString.toCharArray();
        StringBuilder output = new StringBuilder();
        int inputLength = input.length;
        for (int i = 0; i < inputLength; i++) {
            switch (input[i]) {
                case 'ą':
                    output.append('a');
                    break;
                case 'ć':
                    output.append('c');
                    break;
                case 'ę':
                    output.append('e');
                    break;
                case 'ł':
                    output.append('l');
                    break;
                case 'ń':
                    output.append('n');
                    break;
                case 'ó':
                    output.append('o');
                    break;
                case 'ś':
                    output.append('s');
                    break;
                case 'ź':
                    output.append('z');
                    break;
                case 'ż':
                    output.append('z');
                    break;
                case 'Ą':
                    output.append('A');
                    break;
                case 'Ć':
                    output.append('C');
                    break;
                case 'Ę':
                    output.append('E');
                    break;
                case 'Ł':
                    output.append('L');
                    break;
                case 'Ń':
                    output.append('N');
                    break;
                case 'Ó':
                    output.append('O');
                    break;
                case 'Ś':
                    output.append('S');
                    break;
                case 'Ź':
                    output.append('Z');
                    break;
                case 'Ż':
                    output.append('Z');
                    break;
                default:
                    output.append(input[i]);
                    break;
            }
        }
        return output.toString();
    }
}
