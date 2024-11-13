package org.example.resume.model;

public class PersonalInfo {
    private String fileName;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String birth;

    public PersonalInfo() {
    }

    public PersonalInfo(String fileName, String name, String email, String address, String phone, String birth) {
        this.fileName = fileName;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.birth = birth;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "PersonalInfo{" +
                "fileName='" + fileName + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}
