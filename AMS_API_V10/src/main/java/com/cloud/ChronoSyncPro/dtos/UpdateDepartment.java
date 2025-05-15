
package com.cloud.ChronoSyncPro.dtos;

public class UpdateDepartment {
    private Integer id;
    private String name;

    // Default constructor
    public UpdateDepartment() {}

    // Constructor with all fields
    public UpdateDepartment(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    // Static builder method to replace @Builder
    public static UpdateDepartmentBuilder builder() {
        return new UpdateDepartmentBuilder();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Builder class
    public static class UpdateDepartmentBuilder {
        private Integer id;
        private String name;

        public UpdateDepartmentBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UpdateDepartmentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UpdateDepartment build() {
            return new UpdateDepartment(id, name);
        }
    }
}