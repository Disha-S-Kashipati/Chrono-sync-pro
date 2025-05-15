
package com.cloud.ChronoSyncPro.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.dtos.InstructorRegisterRequest;
import com.cloud.ChronoSyncPro.dtos.UpdateInstructor;
import com.cloud.ChronoSyncPro.entity.Instructor;
import com.cloud.ChronoSyncPro.entity.Role;
import com.cloud.ChronoSyncPro.entity.UserAuth;
import com.cloud.ChronoSyncPro.repository.InstructorRepository;
import com.cloud.ChronoSyncPro.repository.OTPRepository;
import com.cloud.ChronoSyncPro.repository.UserAuthRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final UserAuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final OTPRepository otpRepository;

    // Constructor
    public InstructorService(InstructorRepository instructorRepository, UserAuthRepository authRepository,
                             PasswordEncoder passwordEncoder, OTPRepository otpRepository) {
        this.instructorRepository = instructorRepository;
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpRepository = otpRepository;
    }

    public void saveInstructor(InstructorRegisterRequest instructorRegisterRequest)
            throws SQLIntegrityConstraintViolationException {

        UserAuth user = new UserAuth();
        user.setEmail(instructorRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(instructorRegisterRequest.getPassword()));
        user.setRole(Role.INSTRUCTOR);

        UserAuth savedUser = authRepository.save(user);

        Instructor instructor = new Instructor();
        instructor.setName(instructorRegisterRequest.getName());
        instructor.setUserAuth(savedUser);
        instructor.setDob(instructorRegisterRequest.getDob());
        instructor.setGender(instructorRegisterRequest.getGender());
        instructor.setDepartment(instructorRegisterRequest.getDepartment());

        instructorRepository.save(instructor);
    }

    public List<Instructor> getAllInstructor() {
        return instructorRepository.findAll();
    }

    public long instructorCount() {
        return instructorRepository.count();
    }

    public UpdateInstructor getInstructorById(Integer id) throws EntityNotFoundException {
        Instructor instructor = instructorRepository.getReferenceById(id);
        System.out.println(instructor.getBatches());

        UpdateInstructor updateInstructor = new UpdateInstructor();
        updateInstructor.setId(instructor.getId());
        updateInstructor.setName(instructor.getName());
        updateInstructor.setDepartment(instructor.getDepartment());
        updateInstructor.setGender(instructor.getGender());
        updateInstructor.setUserAuth(instructor.getUserAuth());
        updateInstructor.setDob(instructor.getDob());
        updateInstructor.setCourses(instructor.getCourses());
        updateInstructor.setBatches(instructor.getBatches());

        return updateInstructor;
    }

    public void updateInstructor(UpdateInstructor updateInstructor) {
        Instructor instructor = instructorRepository.findById(updateInstructor.getId())
                .orElseThrow(() -> new EntityNotFoundException("Instructor not found with id " + updateInstructor.getId()));

        instructor.setName(updateInstructor.getName());
        instructor.setDepartment(updateInstructor.getDepartment());
        instructor.setGender(updateInstructor.getGender());
        instructor.setDob(updateInstructor.getDob());
        instructor.getUserAuth().setEmail(updateInstructor.getUserAuth().getEmail());
        instructor.getUserAuth().setRole(updateInstructor.getUserAuth().getRole());
        instructor.setCourses(updateInstructor.getCourses());
        instructor.setBatches(updateInstructor.getBatches());

        instructorRepository.save(instructor);
    }

    public void deleteInstructor(Integer id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Instructor not Found"));

        otpRepository.deleteByUserAuth(instructor.getUserAuth());

        instructor.getCourses().forEach(course -> course.getInstructors().remove(instructor));
        instructor.getBatches().forEach(batch -> batch.getInstructors().remove(instructor));
        instructorRepository.delete(instructor);
    }
}
