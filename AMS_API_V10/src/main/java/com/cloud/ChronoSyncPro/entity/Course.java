package com.cloud.ChronoSyncPro.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 60, nullable = false)
    private String courseName;

    @Column(length = 25, nullable = false, unique = true)
    private String courseCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseType courseType;

    @Column(nullable = false)
    private String semester;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "course_instructors", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Instructor> instructors;

    // Default constructor
    public Course() {}

    // Constructor with all fields
    public Course(Integer id, String courseName, String courseCode, CourseType courseType, String semester, List<Instructor> instructors) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseType = courseType;
        this.semester = semester;
        this.instructors = instructors;
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

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    // equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id != null && id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseType=" + courseType +
                ", semester='" + semester + '\'' +
                ", instructors=" + instructors +
                '}';
    }
}
