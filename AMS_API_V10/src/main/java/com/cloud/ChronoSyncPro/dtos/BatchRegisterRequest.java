package com.cloud.ChronoSyncPro.dtos;

import java.util.List;


import com.cloud.ChronoSyncPro.entity.BatchType;
import com.cloud.ChronoSyncPro.entity.Course;
import com.cloud.ChronoSyncPro.entity.Department;

public class BatchRegisterRequest {

    private String batchName;
    private Department department;
    private String semester;
    private BatchType batchType;
    private List<Course> courses;

    // Default constructor
    public BatchRegisterRequest() {}

    // Constructor with all fields
    public BatchRegisterRequest(String batchName, Department department, String semester, BatchType batchType, List<Course> courses) {
        this.batchName = batchName;
        this.department = department;
        this.semester = semester;
        this.batchType = batchType;
        this.courses = courses;
    }

    // Getters and Setters
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public BatchType getBatchType() {
        return batchType;
    }

    public void setBatchType(BatchType batchType) {
        this.batchType = batchType;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
