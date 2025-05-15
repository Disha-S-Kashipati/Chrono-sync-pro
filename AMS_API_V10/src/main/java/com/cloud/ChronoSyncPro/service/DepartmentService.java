package com.cloud.ChronoSyncPro.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloud.ChronoSyncPro.dtos.DepartmentRegisterRequest;
import com.cloud.ChronoSyncPro.dtos.UpdateDepartment;
import com.cloud.ChronoSyncPro.entity.Department;
import com.cloud.ChronoSyncPro.repository.DepartmentRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class DepartmentService {
    
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department saveDepartment(DepartmentRegisterRequest request) {
        try {
            Department department = new Department();
            department.setName(request.getName());
            return departmentRepository.save(department);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Department name must be unique", e);
        }
    }

    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public long departmentCount() {
        return departmentRepository.count();
    }

    @Transactional(readOnly = true)
    public UpdateDepartment getDepartmentById(Integer id) {
        Department department = departmentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
        
        UpdateDepartment updateDepartment = new UpdateDepartment();
        updateDepartment.setId(department.getId());
        updateDepartment.setName(department.getName());
        return updateDepartment;
    }

    public Department updateDepartment(UpdateDepartment updateRequest) {
        try {
            // Check if department exists
            if (!departmentRepository.existsById(updateRequest.getId())) {
                throw new EntityNotFoundException("Department not found with id: " + updateRequest.getId());
            }

            Department department = new Department();
            department.setId(updateRequest.getId());
            department.setName(updateRequest.getName());
            
            return departmentRepository.save(department);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Department name must be unique", e);
        }
    }

    public void deleteDepartmentById(Integer id) {
        if (!departmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Department not found with id: " + id);
        }
        try {
            departmentRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Cannot delete department because it is referenced by other entities", e);
        }
    }
}