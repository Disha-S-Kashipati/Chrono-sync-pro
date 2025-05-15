package com.cloud.ChronoSyncPro.dtos;

import com.cloud.ChronoSyncPro.entity.CourseType;

public class CourseAttendance {

    private Integer id;
    private String courseName;
    private String courseCode;
    private CourseType courseType;
    private int totalDays;
    private int daysPresent;

    public CourseAttendance() {}

    public CourseAttendance(Integer id, String courseName, String courseCode, CourseType courseType, int totalDays, int daysPresent) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseType = courseType;
        this.totalDays = totalDays;
        this.daysPresent = daysPresent;
    }

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

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(int totalDays) {
        this.totalDays = totalDays;
    }

    public int getDaysPresent() {
        return daysPresent;
    }

    public void setDaysPresent(int daysPresent) {
        this.daysPresent = daysPresent;
    }

    @Override
    public String toString() {
        return "CourseAttendance{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseType=" + courseType +
                ", totalDays=" + totalDays +
                ", daysPresent=" + daysPresent +
                '}';
    }
}
