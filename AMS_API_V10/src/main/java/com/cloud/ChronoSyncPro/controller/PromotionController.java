package com.cloud.ChronoSyncPro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ChronoSyncPro.dtos.PromoteGroup;
import com.cloud.ChronoSyncPro.service.PromotionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true", allowedHeaders = "*")
public class PromotionController {

    private final PromotionService promotionService;
    private static final Logger log = LoggerFactory.getLogger(PromotionController.class);

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @PostMapping("/promote")
    public ResponseEntity<?> promoteStudents(@RequestBody List<Integer> student_ids) {
        log.info("Promote Students");
        System.out.println(student_ids);
        try {
            promotionService.promoteStudents(student_ids);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/promote-group")
    public ResponseEntity<?> promoteFinalYears() {
        log.info("Promote Students");
        List<PromoteGroup> promoteFinalYears = null;
        try {
            promoteFinalYears = promotionService.promoteFinalYears();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        }
        return new ResponseEntity<>(promoteFinalYears, HttpStatus.OK);
    }
    
    @PostMapping("/removeStudents")
    public ResponseEntity<?> removeStudents(@RequestBody List<Integer> studentIds) {
        promotionService.removeStudentGroup(studentIds);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
