package com.cloud.ChronoSyncPro.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.ChronoSyncPro.entity.Course;
import com.cloud.ChronoSyncPro.entity.Instructor;

import jakarta.persistence.EntityNotFoundException;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	public void deleteCourseById(Integer id) throws EntityNotFoundException;
	
	List<Course> findByInstructors(Instructor instructor);
	
	
}