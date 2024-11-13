package org.example.resume.model;

public class Education {
    private String GraduationYear;
    private String schoolName;
    private String major;
    private String graduationStatus;

    public Education() {
    }

    public Education(String graduationYear, String schoolName, String major, String graduationStatus) {
        GraduationYear = graduationYear;
        this.schoolName = schoolName;
        this.major = major;
        this.graduationStatus = graduationStatus;
    }

    public String getGraduationYear() {
        return GraduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        GraduationYear = graduationYear;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGraduationStatus() {
        return graduationStatus;
    }

    public void setGraduationStatus(String graduationStatus) {
        this.graduationStatus = graduationStatus;
    }

    @Override
    public String toString() {
        return "Education{" +
                "GraduationYear='" + GraduationYear + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", major='" + major + '\'' +
                ", graduationStatus='" + graduationStatus + '\'' +
                '}';
    }
}
