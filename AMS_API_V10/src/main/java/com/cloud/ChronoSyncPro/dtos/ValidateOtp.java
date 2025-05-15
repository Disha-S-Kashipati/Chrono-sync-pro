package com.cloud.ChronoSyncPro.dtos;

public class ValidateOtp {

    private String email;
    private Integer otp;

    // Default constructor
    public ValidateOtp() {}

    // Constructor with all fields
    public ValidateOtp(String email, Integer otp) {
        this.email = email;
        this.otp = otp;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }
}
