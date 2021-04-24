package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.UserRepository;

import java.util.*;


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

    Set<String> studentNamesM = new HashSet<String>(
            Set.of("Artur", "Michał", "Marcin", "Mateusz", "Krzysztof", "Cezary", "Maciej", "Piotr", "Paweł", "Adrian", "Kamil", "Sebastian"));
    Set<String> studentNamesF = new HashSet<String>(
            Set.of("Anna", "Dorota", "Katarzyna", "Karolina", "Justyna", "Beata", "Julia", "Marta", "Natalia", "Kamila", "Małgorzata"));
    Set<String> studentLastNamesM = new HashSet<String>(
            Set.of("Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński", "Szymański", "Woźniak"));
    Set<String> studentLastNamesF = new HashSet<String>(
            Set.of("Nowak", "Kowalska", "Wiśniewska", "Wójcik", "Kowalczyk", "Kamińska", "Lewandowska", "Zielińska", "Szymańska", "Woźniak", "Dąbrowska"));
    Set<String> emails = new HashSet<String>(
            Set.of("@tlen.pl", "@gmail.com", "@onet.pl", "@utlook.com", "@AOL.com"));

    public void generateRandomStudent(Integer amount){
//        int half = amount / 2;
//
//        System.out.println("++++++++++++++++++++");
//        System.out.println(half);
//        System.out.println("++++++++++++++++++++");
//        Set studentSet = new HashSet<Student>();
//        studentNamesM.
    }
}
