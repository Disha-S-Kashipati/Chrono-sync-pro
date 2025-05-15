package com.cloud.ChronoSyncPro.repository;

import java.util.List;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.ChronoSyncPro.entity.Instructor;
import com.cloud.ChronoSyncPro.entity.UserAuth;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

	List<Instructor> findInstructorByDepartment_Id(Integer id);
	
	Optional<Instructor> findByUserAuth(UserAuth userAuth);

}