package com.cloud.ChronoSyncPro.service;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.entity.Attendance;
import com.cloud.ChronoSyncPro.entity.Student;
import com.cloud.ChronoSyncPro.repository.AttendanceRepository;

import java.util.List;
import java.time.LocalDate;

@Service
@Transactional
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Transactional(readOnly = true)
    public List<Attendance> getAllAttendanceRecords() {
        return attendanceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceByStudent(Student student) {
        return attendanceRepository.findByStudent(student);
    }

    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceByStudentId(Integer studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<Attendance> getAttendanceByStudentIds(List<Integer> studentIds) {
        return attendanceRepository.findAllByStudentIdIn(studentIds);
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    public void deleteAttendanceByStudentId(Integer studentId) {
        attendanceRepository.deleteByStudentId(studentId);
    }
}