package com.cloud.ChronoSyncPro.dtos;

import java.util.Date;
import java.util.List;

import com.cloud.ChronoSyncPro.entity.Batch;
import com.cloud.ChronoSyncPro.entity.Department;
import com.cloud.ChronoSyncPro.entity.Gender;
import com.cloud.ChronoSyncPro.entity.UserAuth;

public class UpdateStudent {

    private Integer id;
    private String name;
    private Department department;
    private Gender gender;
    private Date dob;
    private UserAuth userAuth;
    private String semester;
    private String registrationNumber;
    private String universityRoll;
    private List<Batch> batches;

    // Default constructor
    public UpdateStudent() {}

    // Constructor with all fields
    public UpdateStudent(Integer id, String name, Department department, Gender gender, Date dob, UserAuth userAuth,
                         String semester, String registrationNumber, String universityRoll, List<Batch> batches) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.dob = dob;
        this.userAuth = userAuth;
        this.semester = semester;
        this.registrationNumber = registrationNumber;
        this.universityRoll = universityRoll;
        this.batches = batches;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getUniversityRoll() {
        return universityRoll;
    }

    public void setUniversityRoll(String universityRoll) {
        this.universityRoll = universityRoll;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }

    // equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateStudent that = (UpdateStudent) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "UpdateStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", gender=" + gender +
                ", dob=" + dob +
                ", userAuth=" + userAuth +
                ", semester='" + semester + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", universityRoll='" + universityRoll + '\'' +
                ", batches=" + batches +
                '}';
    }
}
