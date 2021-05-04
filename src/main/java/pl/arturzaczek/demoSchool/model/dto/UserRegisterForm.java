package pl.arturzaczek.demoSchool.model.dto;




public class UserRegisterForm {
    private String formName;
    private String formLastName;
    private String email;
    private String password;

    public UserRegisterForm() {
    }

    public UserRegisterForm(String formName, String formLastName, String email, String password) {
        this.formName = formName;
        this.formLastName = formLastName;
        this.email = email;
        this.password = password;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormLastName() {
        return formLastName;
    }

    public void setFormLastName(String formLastName) {
        this.formLastName = formLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
