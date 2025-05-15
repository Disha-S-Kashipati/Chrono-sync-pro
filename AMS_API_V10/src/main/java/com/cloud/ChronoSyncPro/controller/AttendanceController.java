
package com.cloud.ChronoSyncPro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cloud.ChronoSyncPro.entity.Attendance;
import com.cloud.ChronoSyncPro.entity.Student;
import com.cloud.ChronoSyncPro.service.AttendanceService;

import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attendance>> getStudentAttendance(@PathVariable Integer studentId) {
        List<Attendance> attendances = attendanceService.getAttendanceByStudentId(studentId);
        return attendances.isEmpty() 
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok(attendances);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Attendance>> getStudentsAttendance(
            @RequestParam List<Integer> studentIds) {
        List<Attendance> attendances = attendanceService.getAttendanceByStudentIds(studentIds);
        return ResponseEntity.ok(attendances);
    }

    @PostMapping
    public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
        try {
            Attendance savedAttendance = attendanceService.saveAttendance(attendance);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity<Void> deleteStudentAttendance(@PathVariable Integer studentId) {
        try {
            attendanceService.deleteAttendanceByStudentId(studentId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}