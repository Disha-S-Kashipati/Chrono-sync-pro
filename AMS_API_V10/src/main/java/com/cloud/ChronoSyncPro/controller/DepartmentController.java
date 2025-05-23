package com.cloud.ChronoSyncPro.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ChronoSyncPro.dtos.DepartmentRegisterRequest;
import com.cloud.ChronoSyncPro.dtos.UpdateDepartment;
import com.cloud.ChronoSyncPro.entity.Department;
import com.cloud.ChronoSyncPro.service.DepartmentService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/saveDepartment")
    public ResponseEntity<?> saveDepartment(@RequestBody DepartmentRegisterRequest departmentRegisterRequest) {
        try {
            departmentService.saveDepartment(departmentRegisterRequest);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAllDepartment")
    public ResponseEntity<List<Department>> getDepartmentList() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/getDepartment/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable("id") Integer id) {
        try {
            UpdateDepartment department = departmentService.getDepartmentById(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/updateDepartment")
    public ResponseEntity<?> updateDepartment(@RequestBody UpdateDepartment updateDepartment) {
        try {
            departmentService.updateDepartment(updateDepartment);
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Integer id) {
        System.out.println(id);
        try {
            departmentService.deleteDepartmentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof DataIntegrityViolationException) {
                return new ResponseEntity<>(HttpStatusCode.valueOf(452));                
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/departmentCount")
    public ResponseEntity<Long> departmentCount() {
        return new ResponseEntity<>(departmentService.departmentCount(), HttpStatus.OK);
    }
}
