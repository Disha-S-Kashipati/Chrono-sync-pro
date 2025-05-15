
package com.cloud.ChronoSyncPro.dtos;

public class CourseRegisterRequest {

    private String courseName;
    private String courseCode;
    private String courseType;
    private String semester;

    // Default constructor
    public CourseRegisterRequest() {}

    // Constructor with all fields
    public CourseRegisterRequest(String courseName, String courseCode, String courseType, String semester) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseType = courseType;
        this.semester = semester;
    }

    // Getters and Setters
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
