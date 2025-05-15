package com.cloud.ChronoSyncPro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.ChronoSyncPro.entity.Batch;
import com.cloud.ChronoSyncPro.entity.Department;
import com.cloud.ChronoSyncPro.entity.Instructor;

public interface BatchRepository extends JpaRepository<Batch, Integer> {
	
	List<Batch> findByInstructors(Instructor instructor);

	List<Batch> findAllByDepartmentAndSemester(Department department, String semester);

}