package com.cloud.ChronoSyncPro.entity;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String batchName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(nullable = false)
    private String semester;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BatchType batchType;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "batch_courses", joinColumns = @JoinColumn(name = "batch_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "batch_instructor", joinColumns = @JoinColumn(name = "batch_id"), inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Instructor> instructors;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "batch_student", joinColumns = @JoinColumn(name = "batch_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    // Default constructor
    public Batch() {}

    // Constructor with all fields
    public Batch(Integer id, String batchName, Department department, String semester, BatchType batchType, List<Course> courses, /*List<Instructor> instructors,*/ List<Student> students) {
        this.id = id;
        this.batchName = batchName;
        this.department = department;
        this.semester = semester;
        this.batchType = batchType;
        this.courses = courses;
       // this.instructors = instructors;
        this.students = students;
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

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Batch batch = (Batch) o;
        return id != null && id.equals(batch.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Batch{" +
                "id=" + id +
                ", batchName='" + batchName + '\'' +
                ", department=" + department +
                ", semester='" + semester + '\'' +
                ", batchType=" + batchType +
                ", courses=" + courses +
                //", instructors=" + instructors +
                ", students=" + students +
                '}';
    }
}
