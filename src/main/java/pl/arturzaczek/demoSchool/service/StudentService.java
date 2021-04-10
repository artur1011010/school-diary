package pl.arturzaczek.demoSchool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.arturzaczek.demoSchool.model.repositories.GradeRepository;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;
import pl.arturzaczek.demoSchool.model.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    GradeRepository gradeRepository;
    StudentRepository studentRepository;
    SubjectRepository subjectRepository;

    @Autowired
    public StudentService(GradeRepository gradeRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    List<String> studentNamesM = new ArrayList<>(
            List.of("Artur", "Michał", "Marcin", "Mateusz", "Krzysztof", "Cezary", "Maciej", "Piotr", "Paweł", "Adrian", "Kamil", "Sebastian"));
    List<String> studentNamesF = new ArrayList<>(
            List.of("Anna", "Dorota", "Katarzyna", "Karolina", "Justyna", "Beata", "Julia", "Marta", "Natalia", "Kamila", "Małgorzata"));
    List<String> studentLastNamesM = new ArrayList<>(
            List.of("Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kowalczyk", "Kamiński", "Lewandowski", "Zieliński", "Szymański", "Woźniak"));
    List<String> studentLastNamesF = new ArrayList<>(
            List.of("Nowak", "Kowalska", "Wiśniewska", "Wójcik", "Kowalczyk", "Kamińska", "Lewandowska", "Zielińska", "Szymańska", "Woźniak", "Dąbrowska"));

}
