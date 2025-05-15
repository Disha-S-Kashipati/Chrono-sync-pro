
package com.cloud.ChronoSyncPro.dtos;

public class DepartmentRegisterRequest {
    private String name;

    // Default constructor
    public DepartmentRegisterRequest() {}

    // Constructor with all fields
    public DepartmentRegisterRequest(String name) {
        this.name = name;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
