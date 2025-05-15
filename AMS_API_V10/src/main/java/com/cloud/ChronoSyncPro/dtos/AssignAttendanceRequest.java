package com.cloud.ChronoSyncPro.dtos;

import java.time.LocalDate;
import java.util.List;

public class AssignAttendanceRequest {

    private List<Integer> studentIds;
    private Integer courseId;
    private Integer batchId;
    private LocalDate date;

    public AssignAttendanceRequest() {}

    public AssignAttendanceRequest(List<Integer> studentIds, Integer courseId, Integer batchId, LocalDate date) {
        this.studentIds = studentIds;
        this.courseId = courseId;
        this.batchId = batchId;
        this.date = date;
    }

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "AssignAttendanceRequest{" +
                "studentIds=" + studentIds +
                ", courseId=" + courseId +
                ", batchId=" + batchId +
                ", date=" + date +
                '}';
    }
}
