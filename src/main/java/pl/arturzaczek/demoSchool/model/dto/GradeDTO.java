package pl.arturzaczek.demoSchool.model.dto;


public class GradeDTO {
    private String subjectName;
    private Integer gradeValue;

    public GradeDTO() {
    }

    public GradeDTO(String subjectName, Integer gradeValue) {
        this.subjectName = subjectName;
        this.gradeValue = gradeValue;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        this.gradeValue = gradeValue;
    }

    @Override
    public String toString() {
        return "GradeDTO: " + subjectName + ", gradeValue: " + gradeValue;
    }
}
