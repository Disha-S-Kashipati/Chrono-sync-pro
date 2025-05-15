package com.cloud.ChronoSyncPro.dtos;

public class AuthenticationResponse {

    private String token;
    private String role;
    private String email;

    // Default constructor
    public AuthenticationResponse() {}

    // Constructor with all fields
    public AuthenticationResponse(String token, String role, String email) {
        this.token = token;
        this.role = role;
        this.email = email;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Static builder method
    public static Builder builder() {
        return new Builder();
    }

    // Builder class
    public static class Builder {
        private String token;
        private String role;
        private String email;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(token, role, email);
        }
    }
}
