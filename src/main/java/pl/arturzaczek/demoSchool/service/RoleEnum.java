package pl.arturzaczek.demoSchool.service;

public enum RoleEnum {
    ROLE_USER("USER"),
    ROLE_TEACHER("TEACHER"),
    ROLE_ADMIN("ADMIN"),
    ROLE_PRINCIPAL("PRINCIPAL"),
    ROLE_STUDENT("STUDENT"),
    ROLE_PARENT("PARENT");

    private String role;

    RoleEnum(String role_name) {
        role = role_name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
