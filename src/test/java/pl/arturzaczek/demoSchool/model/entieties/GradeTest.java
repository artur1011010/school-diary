package pl.arturzaczek.demoSchool.model.entieties;


import org.junit.jupiter.api.Test;
import pl.arturzaczek.demoSchool.model.entities.Grade;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeTest {

    @Test
    void gradeAssigmentTest_OK_1() {
        Grade grade = new Grade(20);
        assertEquals(20, grade.getGradeValue());
    }

    @Test
    void gradeAssigmentTest_NOK_1() {
        Grade grade = new Grade(-15);
        assertEquals(0, grade.getGradeValue());
    }
    @Test
    void gradeAssigmentTest_NOK_2() {
        Grade grade = new Grade(101);
        assertEquals(0, grade.getGradeValue());
    }
    @Test
    void gradeAssigmentTest_NOK_3() {
        Grade grade = new Grade(-1);
        assertEquals(0, grade.getGradeValue());
    }
}
