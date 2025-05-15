
package com.cloud.ChronoSyncPro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.dtos.AssignInstructorToCourseRequest;
import com.cloud.ChronoSyncPro.entity.Course;
import com.cloud.ChronoSyncPro.entity.Instructor;
import com.cloud.ChronoSyncPro.repository.CourseRepository;
import com.cloud.ChronoSyncPro.repository.InstructorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InstructorCourseService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    // Constructor
    public InstructorCourseService(InstructorRepository instructorRepository, CourseRepository courseRepository) {
        this.instructorRepository = instructorRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void assignInstructorToCourses(AssignInstructorToCourseRequest assignInstructorToCourseRequest) {
        // Retrieve the Instructor
        Optional<Instructor> instructorOptional = instructorRepository.findById(assignInstructorToCourseRequest.getId());

        if (instructorOptional.isPresent()) {
            Instructor instructor = instructorOptional.get();

            List<Course> findByInstructors = courseRepository.findByInstructors(instructor);

            findByInstructors.forEach(course -> {
                course.getInstructors().remove(instructor);
            });

            if (instructor.getCourses() == null) {
                instructor.setCourses(new ArrayList<>());
            } else {
                instructor.getCourses().clear();
            }

            // Retrieve the Courses
            List<Course> courses = courseRepository.findAllById(
                    assignInstructorToCourseRequest.getCourses().stream().map(Course::getId).toList());

            // Update the relationship for each course
            courses.forEach(course -> {
                if (course.getInstructors() == null) {
                    course.setInstructors(new ArrayList<>());
                }

                if (!course.getInstructors().contains(instructor)) {
                    course.getInstructors().add(instructor);
                }
                instructor.getCourses().add(course);
            });

            // Persist the changes
            courseRepository.saveAll(courses);
            instructorRepository.save(instructor);
        } else {
            throw new EntityNotFoundException("Instructor not found");
        }
    }
}
