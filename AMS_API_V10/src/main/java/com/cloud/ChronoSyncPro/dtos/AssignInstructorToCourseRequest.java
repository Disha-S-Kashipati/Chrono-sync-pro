package com.cloud.ChronoSyncPro.dtos;

import java.util.List;

import com.cloud.ChronoSyncPro.entity.Course;

public class AssignInstructorToCourseRequest {
    
    private Integer id;
    private List<Course> courses;

    // Default constructor
    public AssignInstructorToCourseRequest() {}

    // Constructor with all fields
    public AssignInstructorToCourseRequest(Integer id, List<Course> courses) {
        this.id = id;
        this.courses = courses;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
