package domain.model;

public class Customer {

    private String customerId;
    private String fullName;
    private String address;
    private String phoneNumber;
    private String typeOfMember;

    private String username;
    private String password;

    public Customer() {
    }

    public Customer(String customerId, String fullName, String address, String phoneNumber, String typeOfMember, String username, String password) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.typeOfMember = typeOfMember;
        this.username = username;
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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


    public String getTypeOfMember() {
        return typeOfMember;
    }

    public void setTypeOfMember(String typeOfMember) {
        this.typeOfMember = typeOfMember;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", typeOfMember='" + typeOfMember + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
