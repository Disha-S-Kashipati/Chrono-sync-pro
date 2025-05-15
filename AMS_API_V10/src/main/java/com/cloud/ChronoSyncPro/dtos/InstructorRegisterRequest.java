
package com.cloud.ChronoSyncPro.dtos;

import java.util.Date;

import com.cloud.ChronoSyncPro.entity.Department;
import com.cloud.ChronoSyncPro.entity.Gender;

public class InstructorRegisterRequest {

    private String name;
    private Department department;
    private Gender gender;
    private Date dob;
    private String email;
    private String password;

    public InstructorRegisterRequest() {}

    public InstructorRegisterRequest(String name, Department department, Gender gender, Date dob, String email, String password) {
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "InstructorRegisterRequest{" +
                "name='" + name + '\'' +
                ", department=" + department +
                ", gender=" + gender +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
