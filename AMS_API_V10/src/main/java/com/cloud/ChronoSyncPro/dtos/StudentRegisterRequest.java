
package com.cloud.ChronoSyncPro.dtos;

import java.util.Date;
import java.util.List;

import com.cloud.ChronoSyncPro.entity.Batch;
import com.cloud.ChronoSyncPro.entity.Department;
import com.cloud.ChronoSyncPro.entity.Gender;

public class StudentRegisterRequest {

    private String name;
    private Gender gender;
    private Date dob;
    private String email;
    private String password;
    private String semester;
    private String registrationNumber;
    private String universityRoll;
    private Department department;
    private List<Batch> batches;

    // Default constructor
    public StudentRegisterRequest() {}

    // Constructor with all fields
    public StudentRegisterRequest(String name, Gender gender, Date dob, String email, String password, String semester,
                                  String registrationNumber, String universityRoll, Department department, List<Batch> batches) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.semester = semester;
        this.registrationNumber = registrationNumber;
        this.universityRoll = universityRoll;
        this.department = department;
        this.batches = batches;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}
