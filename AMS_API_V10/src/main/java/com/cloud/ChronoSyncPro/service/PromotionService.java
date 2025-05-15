

package com.cloud.ChronoSyncPro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cloud.ChronoSyncPro.dtos.PromoteGroup;
import com.cloud.ChronoSyncPro.entity.Attendance;
import com.cloud.ChronoSyncPro.entity.Batch;
import com.cloud.ChronoSyncPro.entity.Student;
import com.cloud.ChronoSyncPro.repository.AttendanceRepository;
import com.cloud.ChronoSyncPro.repository.BatchRepository;
import com.cloud.ChronoSyncPro.repository.StudentRepository;

@Service
public class PromotionService {

    private final StudentRepository studentRepository;
    private final BatchRepository batchRepository;
    private final AttendanceRepository attendanceRepository;

    // Constructor
    public PromotionService(StudentRepository studentRepository, BatchRepository batchRepository, AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.batchRepository = batchRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public void promoteStudents(List<Integer> student_Ids) throws RuntimeException {
        List<Student> allStudents = studentRepository.findAllById(student_Ids);

        if (!allStudents.isEmpty()) {
            String semester = allStudents.get(0).getSemester().substring(0, 4)
                    + (Integer.parseInt(allStudents.get(0).getSemester().substring(4)) + 1);
            System.out.println(semester);

            List<Batch> allBatches = batchRepository.findAllByDepartmentAndSemester(allStudents.get(0).getDepartment(),
                    semester);
            allBatches.forEach(batch -> {
                if (!batch.getStudents().isEmpty()) {
                    throw new RuntimeException("Batch not empty");
                }
            });

            allStudents.forEach(student -> {
                List<Batch> list = new ArrayList<>();
                student.getBatches().forEach(batch -> {
                    list.addAll(allBatches.stream()
                            .filter(nextBatch -> nextBatch.getBatchName().equals(batch.getBatchName())).toList());
                    batch.getStudents().remove(student);
                });
                student.getBatches().clear();
                student.setBatches(list);
                student.setSemester(semester);
            });

            allStudents.forEach(student -> {
                allBatches.forEach(batch -> {
                    if (student.getBatches().contains(batch)) {
                        batch.getStudents().add(student);
                    }
                });
            });
            studentRepository.saveAll(allStudents);
            batchRepository.saveAll(allBatches);
        }
    }

    public List<PromoteGroup> promoteFinalYears() throws RuntimeException {
        List<Object[]> results = studentRepository.findStudentCountByDepartmentAndSemester();
        List<PromoteGroup> promoteGroups = new ArrayList<>();
        for (Object[] result : results) {
            PromoteGroup promoteGroup = new PromoteGroup(result);
            promoteGroups.add(promoteGroup);
        }
        System.out.println(promoteGroups);
        return promoteGroups;
    }

    public void removeStudentGroup(List<Integer> studentIds) {
        List<Student> allStudent = studentRepository.findAllById(studentIds);
        allStudent.forEach(student -> {
            student.getBatches().forEach(batch -> {
                batch.getStudents().remove(student);
            });
        });

        attendanceRepository.deleteAllById(attendanceRepository.findAllByStudentIdIn(studentIds).stream().map(Attendance::getId).toList());

        studentRepository.deleteAllById(studentIds);
    }
}
