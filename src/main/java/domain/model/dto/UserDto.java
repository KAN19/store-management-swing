package domain.model.dto;

public class UserDto {
    private String username;
    private String fullName;
    private String phoneNumber;
    private String role;
    private String typeOfMember;

    public UserDto(String username, String fullName, String phoneNumber, String role, String typeOfMember) {
        this.username = username;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.typeOfMember = typeOfMember;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
