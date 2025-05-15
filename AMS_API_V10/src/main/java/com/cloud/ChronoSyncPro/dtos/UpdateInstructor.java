package com.cloud.ChronoSyncPro.dtos;

import java.util.Date;
import java.util.List;

import com.cloud.ChronoSyncPro.entity.Batch;
import com.cloud.ChronoSyncPro.entity.Course;
import com.cloud.ChronoSyncPro.entity.Department;
import com.cloud.ChronoSyncPro.entity.Gender;
import com.cloud.ChronoSyncPro.entity.UserAuth;

public class UpdateInstructor {

    private Integer id;
    private String name;
    private Department department;
    private Gender gender;
    private Date dob;
    private UserAuth userAuth;
    private List<Course> courses;
    private List<Batch> batches;

    // Default constructor
    public UpdateInstructor() {}

    // Constructor with all fields
    public UpdateInstructor(Integer id, String name, Department department, Gender gender, Date dob, UserAuth userAuth, List<Course> courses, List<Batch> batches) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gender = gender;
        this.dob = dob;
        this.userAuth = userAuth;
        this.courses = courses;
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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
        UpdateInstructor that = (UpdateInstructor) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "UpdateInstructor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", gender=" + gender +
                ", dob=" + dob +
                ", userAuth=" + userAuth +
                ", courses=" + courses +
                ", batches=" + batches +
                '}';
    }
}
