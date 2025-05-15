package com.cloud.ChronoSyncPro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.dtos.AssignInstructorToBatchRequest;
import com.cloud.ChronoSyncPro.entity.Batch;
import com.cloud.ChronoSyncPro.entity.Instructor;
import com.cloud.ChronoSyncPro.repository.BatchRepository;
import com.cloud.ChronoSyncPro.repository.InstructorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class InstructorBatchService {

    private final InstructorRepository instructorRepository;
    private final BatchRepository batchRepository;

    // Constructor
    public InstructorBatchService(InstructorRepository instructorRepository, BatchRepository batchRepository) {
        this.instructorRepository = instructorRepository;
        this.batchRepository = batchRepository;
    }

    @Transactional
    public void assignInstructorToBatch(AssignInstructorToBatchRequest assignInstructorToBatchRequest) {
        // Retrieve the Instructor
        Optional<Instructor> instructorOptional = instructorRepository.findById(assignInstructorToBatchRequest.getId());

        if (instructorOptional.isPresent()) {
            Instructor instructor = instructorOptional.get();
            
            List<Batch> findByInstructors = batchRepository.findByInstructors(instructor);
            
            findByInstructors.forEach(batch -> {
                batch.getInstructors().remove(instructor);
            });

            if (instructor.getBatches() == null) {
                instructor.setBatches(new ArrayList<>());
            } else {
                instructor.getBatches().clear();
            }
            
            // Retrieve the Batches
            List<Batch> batches = batchRepository.findAllById(
                    assignInstructorToBatchRequest.getBatches().stream().map(Batch::getId).toList());

            // Update the relationship for each batch
            batches.forEach(batch -> {
                if (batch.getInstructors() == null) {
                    batch.setInstructors(new ArrayList<>());
                }
                
                if (!batch.getInstructors().contains(instructor)) {
                    batch.getInstructors().add(instructor);
                }
                instructor.getBatches().add(batch);
            });

            // Persist the changes
            batchRepository.saveAll(batches);
            instructorRepository.save(instructor);
        } else {
            throw new EntityNotFoundException("Instructor not found");
        }
    }
}
