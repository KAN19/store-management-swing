package domain.model;

public class User {

    private Long Id;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String role;
    private String typeOfMember;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTypeOfMember() {
        return typeOfMember;
    }

    public void setTypeOfMember(String typeOfMember) {
        this.typeOfMember = typeOfMember;
    }
}
