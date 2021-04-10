package pl.arturzaczek.demoSchool.model.dto;


public class GradeDTO {
    private String subjectName;
    private Long id;
    private Integer gradeValue;

    public GradeDTO() {
    }

    public GradeDTO(String subjectName, Long id, Integer gradeValue) {
        this.subjectName = subjectName;
        this.id = id;
        this.gradeValue = gradeValue;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        this.gradeValue = gradeValue;
    }
}
