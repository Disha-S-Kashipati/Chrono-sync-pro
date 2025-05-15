package com.cloud.ChronoSyncPro.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.ChronoSyncPro.entity.Attendance;
import com.cloud.ChronoSyncPro.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	List<Attendance> findByStudent(Student student);
	List<Attendance> findByStudentId(Integer studentId);

	List<Attendance> findAllByStudentIdIn(List<Integer> studentId);
	
	public void deleteByStudentId(Integer studentId);
}
