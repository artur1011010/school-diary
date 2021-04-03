package pl.arturzaczek.demoSchool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.arturzaczek.demoSchool.model.entities.Student;
import pl.arturzaczek.demoSchool.model.repositories.StudentRepository;


import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {

    StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    @GetMapping("/addStudent")
    public String addStudent(){
        List studentlist = new ArrayList<Student>();
        studentlist.add(new Student("Artur", "Aaaaa"));
        studentlist.add(new Student("Marcin", "Bbbbb"));
        studentlist.add(new Student("Mariusz", "Cccc"));
        studentlist.add(new Student("Anna", "Dddd"));
        studentlist.add(new Student("Kamila", "Eeeee"));
        studentRepository.saveAll(studentlist);
        System.out.println("Add student");
        return "students/addStudent";
    }

    @GetMapping("/studentsList")
    public String getStudentsList(){
        List<Student> all = studentRepository.findAll();
        all.forEach(element -> System.out.println(element.toString()));
        return "students/studentsList";
    }
}
