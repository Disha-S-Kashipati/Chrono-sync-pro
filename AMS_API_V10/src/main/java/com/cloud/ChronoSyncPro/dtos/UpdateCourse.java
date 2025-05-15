
package com.cloud.ChronoSyncPro.dtos;

public class UpdateCourse {

    private Integer id;
    private String courseName;
    private String courseCode;
    private String courseType;
    private String semester;

    // Default constructor
    public UpdateCourse() {}

    // Constructor with all fields
    public UpdateCourse(Integer id, String courseName, String courseCode, String courseType, String semester) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseType = courseType;
        this.semester = semester;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
