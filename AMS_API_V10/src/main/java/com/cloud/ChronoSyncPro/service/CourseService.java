package com.cloud.ChronoSyncPro.service;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.dtos.CourseRegisterRequest;
import com.cloud.ChronoSyncPro.dtos.UpdateCourse;
import com.cloud.ChronoSyncPro.entity.Course;
import com.cloud.ChronoSyncPro.entity.CourseType;
import com.cloud.ChronoSyncPro.entity.Instructor;
import com.cloud.ChronoSyncPro.repository.CourseRepository;
import com.cloud.ChronoSyncPro.repository.InstructorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    // Constructor
    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public void saveCourse(CourseRegisterRequest courseRegisterRequest)
            throws SQLIntegrityConstraintViolationException {
        Course course = new Course();
        course.setCourseName(courseRegisterRequest.getCourseName());
        course.setCourseCode(courseRegisterRequest.getCourseCode());
        course.setCourseType(CourseType.valueOf(courseRegisterRequest.getCourseType()));
        course.setSemester(courseRegisterRequest.getSemester());

        courseRepository.save(course);
    }

    public void updateCourse(UpdateCourse updateCourse) throws SQLIntegrityConstraintViolationException {
        Course course = courseRepository.findById(updateCourse.getId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + updateCourse.getId()));
        course.setCourseName(updateCourse.getCourseName());
        course.setCourseCode(updateCourse.getCourseCode());
        course.setCourseType(CourseType.valueOf(updateCourse.getCourseType()));
        course.setSemester(updateCourse.getSemester());

        courseRepository.save(course);
    }

    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    public long courseCount() {
        return courseRepository.count();
    }


    public UpdateCourse getCourse(Integer id) throws EntityNotFoundException {
        Course courseToBeUpdated = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
        return new UpdateCourse(courseToBeUpdated.getId(), courseToBeUpdated.getCourseName(),
                courseToBeUpdated.getCourseCode(), courseToBeUpdated.getCourseType().toString(),
                courseToBeUpdated.getSemester());
    }

    public void deleteCourse(Integer id) throws EntityNotFoundException {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
        List<Instructor> instructors = course.getInstructors();
        for (Instructor instructor : instructors) {
            instructor.getCourses().remove(course);
            instructorRepository.save(instructor);
        }
        courseRepository.delete(course);
    }
}
