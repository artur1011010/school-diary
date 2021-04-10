package pl.arturzaczek.demoSchool.model.dto;


public class SubjectDTO {
    private String subjectName;

    public SubjectDTO(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
