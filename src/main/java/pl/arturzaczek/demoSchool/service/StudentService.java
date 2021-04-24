package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public StudentService(GradeRepository gradeRepository, UserRepository userRepository) {
        this.gradeRepository = gradeRepository;
        this.userRepository = userRepository;
    }

    List<String> studentNamesM = new ArrayList<>(
            Set.of("Artur", "Michał", "Marcin", "Mateusz", "Krzysztof", "Piotr", "Paweł", "Adrian", "Kamil", "Sebastian"));
    List<String> studentNamesF = new ArrayList<>(
            Set.of("Anna", "Dorota", "Katarzyna", "Justyna", "Beata", "Julia", "Marta", "Natalia", "Kamila", "Małgorzata"));
    List<String> studentLastNamesM = new ArrayList<>(
            Set.of("Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński", "Szymański", "Woźniak"));
    List<String> studentLastNamesF = new ArrayList<>(
            Set.of("Nowak", "Kowalska", "Wiśniewska", "Kowalczyk", "Kamińska", "Lewandowska", "Zielińska", "Szymańska", "Woźniak", "Dąbrowska"));
    List<String> emails = new ArrayList<>(
            Set.of("@tlen.pl", "@gmail.com", "@onet.pl", "@utlook.com", "@AOL.com"));

    public List<User> createRandomUserM() {
        Collections.shuffle(studentNamesM);
        Collections.shuffle(studentLastNamesM);
        Collections.shuffle(emails);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < studentLastNamesM.size(); i++) {
            User user = new User();
            user.setFirstName(studentNamesM.get(i));
            user.setLastName(studentLastNamesM.get(i));
            user.setEmail(studentNamesM.get(i) + "." + studentLastNamesM.get(i) + emails.get(i / 2));
            user.setBirthDate(between());
            users.add(user);
        }
        return users;
    }


    public List<User> createRandomUserF() {
        Collections.shuffle(studentNamesF);
        Collections.shuffle(studentLastNamesF);
        Collections.shuffle(emails);
        List<User> users = new ArrayList<>();
        for (int i = 0; i < studentLastNamesF.size(); i++) {
            User user = new User();
            user.setFirstName(studentNamesF.get(i));
            user.setLastName(studentLastNamesF.get(i));
            user.setEmail(studentNamesF.get(i) + "." + studentLastNamesF.get(i) + emails.get(i / 2));
            user.setBirthDate(between());
            users.add(user);
        }
        return users;
    }

    public LocalDate between() {
        LocalDate startInclusive = LocalDate.of(1970, 1, 1);
        LocalDate endExclusive = LocalDate.of(2005, 12, 30);
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}
