package com.cloud.ChronoSyncPro.dtos;

import com.cloud.ChronoSyncPro.entity.BatchType;
import com.cloud.ChronoSyncPro.entity.Department;

public class BatchesOfInstructor {

    private Integer batchId;
    private String batchName;
    private BatchType batchType;
    private Department department;
    private String semester;
    private Integer CourseId;
    private String CourseName;

    public BatchesOfInstructor() {}

    public BatchesOfInstructor(Integer batchId, String batchName, BatchType batchType, Department department, String semester, Integer CourseId, String CourseName) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.batchType = batchType;
        this.department = department;
        this.semester = semester;
        this.CourseId = CourseId;
        this.CourseName = CourseName;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public BatchType getBatchType() {
        return batchType;
    }

    public void setBatchType(BatchType batchType) {
        this.batchType = batchType;
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

    public Integer getCourseId() {
        return CourseId;
    }

    public void setCourseId(Integer courseId) {
        this.CourseId = courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        this.CourseName = courseName;
    }

    @Override
    public String toString() {
        return "BatchesOfInstructor{" +
                "batchId=" + batchId +
                ", batchName='" + batchName + '\'' +
                ", batchType=" + batchType +
                ", department=" + department +
                ", semester='" + semester + '\'' +
                ", CourseId=" + CourseId +
                ", CourseName='" + CourseName + '\'' +
                '}';
    }
}
