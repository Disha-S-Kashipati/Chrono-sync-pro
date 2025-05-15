package com.cloud.ChronoSyncPro.dtos;

import java.util.List;

import com.cloud.ChronoSyncPro.entity.Batch;

public class AssignInstructorToBatchRequest {

    private Integer id;
    private List<Batch> batches;

    // Default constructor
    public AssignInstructorToBatchRequest() {}

    // Constructor with all fields
    public AssignInstructorToBatchRequest(Integer id, List<Batch> batches) {
        this.id = id;
        this.batches = batches;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Batch> getBatches() {
        return batches;
    }

    public void setBatches(List<Batch> batches) {
        this.batches = batches;
    }
}
