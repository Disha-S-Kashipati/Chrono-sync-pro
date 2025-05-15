package com.cloud.ChronoSyncPro.dtos;

import java.util.List;

import com.cloud.ChronoSyncPro.entity.BatchType;
import com.cloud.ChronoSyncPro.entity.Course;
import com.cloud.ChronoSyncPro.entity.Department;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateBatch {

    @JsonProperty("id")
    private Integer id;
    
    @JsonProperty("batchName")
    private String batchName;
    
    @JsonProperty("department")
    private Department department;
    
    @JsonProperty("semester")
    private String semester;
    
    @JsonProperty("batchType")
    private BatchType batchType;
    
    @JsonProperty("courses")
    private List<Course> courses;

    // Default constructor
    public UpdateBatch() {}

    // Constructor with all fields
    public UpdateBatch(Integer id, String batchName, Department department, String semester, BatchType batchType, List<Course> courses) {
        this.id = id;
        this.batchName = batchName;
        this.department = department;
        this.semester = semester;
        this.batchType = batchType;
        this.courses = courses;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        StringBuilder courseInfo = new StringBuilder();
        if (courses != null) {
            for (Course course : courses) {
                courseInfo.append("\n    ").append(course);
            }
        }

        return "UpdateBatch{" +
                "\n  id=" + id +
                "\n  batchName='" + batchName + '\'' +
                "\n  department=" + department +
                "\n  semester='" + semester + '\'' +
                "\n  batchType=" + batchType +
                "\n  courses=[" + courseInfo + "\n  ]" +
                "\n}";
    }
}