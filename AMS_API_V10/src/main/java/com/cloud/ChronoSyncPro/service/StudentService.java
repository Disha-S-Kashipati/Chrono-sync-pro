package com.cloud.ChronoSyncPro.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.dtos.StudentRegisterRequest;
import com.cloud.ChronoSyncPro.dtos.UpdateStudent;
import com.cloud.ChronoSyncPro.entity.Batch;
import com.cloud.ChronoSyncPro.entity.Role;
import com.cloud.ChronoSyncPro.entity.Student;
import com.cloud.ChronoSyncPro.entity.UserAuth;
import com.cloud.ChronoSyncPro.repository.AttendanceRepository;
import com.cloud.ChronoSyncPro.repository.BatchRepository;
import com.cloud.ChronoSyncPro.repository.OTPRepository;
import com.cloud.ChronoSyncPro.repository.StudentRepository;
import com.cloud.ChronoSyncPro.repository.UserAuthRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserAuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final BatchRepository batchRepository;
    private final OTPRepository otpRepository;
    private final AttendanceRepository attendanceRepository;

    // Constructor
    public StudentService(StudentRepository studentRepository, UserAuthRepository authRepository, PasswordEncoder passwordEncoder,
                          BatchRepository batchRepository, OTPRepository otpRepository, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.batchRepository = batchRepository;
        this.otpRepository = otpRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public Student saveStudent(StudentRegisterRequest studentRegisterRequest)
            throws DataIntegrityViolationException {

        UserAuth user = new UserAuth();
        user.setEmail(studentRegisterRequest.getEmail());
        user.setPassword(passwordEncoder.encode(studentRegisterRequest.getPassword()));
        user.setRole(Role.STUDENT);
        UserAuth savedUser = authRepository.save(user);

        Student student = new Student();
        student.setName(studentRegisterRequest.getName());
        student.setUserAuth(savedUser);
        student.setSemester(studentRegisterRequest.getSemester());
        student.setDob(studentRegisterRequest.getDob());
        student.setGender(studentRegisterRequest.getGender());
        student.setDepartment(studentRegisterRequest.getDepartment());
        student.setRegistrationNumber(studentRegisterRequest.getRegistrationNumber());
        student.setUniversityRoll(studentRegisterRequest.getUniversityRoll());

        Student savedStudent = studentRepository.save(student);
        assignBatchToStudent(savedStudent, studentRegisterRequest.getBatches());
        return savedStudent;
    }

    public void assignBatchToStudent(Student student, List<Batch> batches) {
        if (student != null) {
            Optional<Student> studentOptional = studentRepository.findById(student.getId());

            if (studentOptional.isPresent()) {
                Student student2 = studentOptional.get();

                // Ensure that the student's batches list is initialized
                if (student2.getBatches() == null) {
                    student2.setBatches(new ArrayList<>());
                }

                // Retrieve and ensure initialization of batch objects
                List<Batch> batchesList = batchRepository.findAllById(batches.stream().map(Batch::getId).toList());

                batchesList.forEach(batch -> {
                    // Ensure that the batch's students list is initialized
                    if (batch.getStudents() == null) {
                        batch.setStudents(new ArrayList<>());
                    }

                    // Update relationship bidirectionally
                    if (!batch.getStudents().contains(student2)) {
                        batch.getStudents().add(student2);
                    }
                    if (!student2.getBatches().contains(batch)) {
                        student2.getBatches().add(batch);
                    }
                });

                // Persist the changes
                batchRepository.saveAll(batchesList);
                studentRepository.save(student2);
            } else {
                throw new EntityNotFoundException("Student not found");
            }
        } else {
            throw new IllegalArgumentException("Student cannot be null");
        }
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public long studentCount() {
        return studentRepository.count();
    }

    public UpdateStudent getStudentById(Integer id) throws EntityNotFoundException {
        Student student = studentRepository.getReferenceById(id);

        UpdateStudent updateStudent = new UpdateStudent();
        updateStudent.setId(student.getId());
        updateStudent.setName(student.getName());
        updateStudent.setDepartment(student.getDepartment());
        updateStudent.setGender(student.getGender());
        updateStudent.setUserAuth(student.getUserAuth());
        updateStudent.setSemester(student.getSemester());
        updateStudent.setDob(student.getDob());
        updateStudent.setRegistrationNumber(student.getRegistrationNumber());
        updateStudent.setUniversityRoll(student.getUniversityRoll());
        updateStudent.setBatches(student.getBatches());

        return updateStudent;
    }

    public void updatestudent(UpdateStudent updateStudent) {
        Student savedStudent = new Student();
        savedStudent.setId(updateStudent.getId());
        savedStudent.setName(updateStudent.getName());
        savedStudent.setUserAuth(updateStudent.getUserAuth());
        savedStudent.setSemester(updateStudent.getSemester());
        savedStudent.setGender(updateStudent.getGender());
        savedStudent.setDepartment(updateStudent.getDepartment());
        savedStudent.setDob(updateStudent.getDob());
        savedStudent.setRegistrationNumber(updateStudent.getRegistrationNumber());
        savedStudent.setUniversityRoll(updateStudent.getUniversityRoll());

        Student saved = studentRepository.save(savedStudent);
        assignBatchToStudent(saved, updateStudent.getBatches());
    }

    public void deleteStudent(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        otpRepository.deleteByUserAuth(student.getUserAuth());
        attendanceRepository.deleteByStudentId(student.getId());

        // Remove references from batches
        student.getBatches().forEach(batch -> batch.getStudents().remove(student));

        studentRepository.delete(student);
    }

    public List<Student> findStudentbyBatchId(Integer batchId) {
        return studentRepository.findAllByBatchesId(batchId);
    }

    public List<Student> getStudentsUsingDepartmentAndSemester(Integer dept_id, String semester) {
        return studentRepository.findStudentByDepartment_IdAndSemester(dept_id, semester);
    }
}
