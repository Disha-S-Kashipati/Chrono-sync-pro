package com.cloud.ChronoSyncPro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ChronoSyncPro.dtos.AssignInstructorToBatchRequest;
import com.cloud.ChronoSyncPro.service.InstructorBatchService;

@RestController
@RequestMapping("/api/v1/admin/instructor-batch")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class InstructorBatchController {

    private final InstructorBatchService instructorBatchService;

    public InstructorBatchController(InstructorBatchService instructorBatchService) {
        this.instructorBatchService = instructorBatchService;
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignInstructorToBatch(@RequestBody AssignInstructorToBatchRequest assignInstructorToBatchRequest) {
        try {
            instructorBatchService.assignInstructorToBatch(assignInstructorToBatchRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
