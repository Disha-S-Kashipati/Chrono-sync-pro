package com.cloud.ChronoSyncPro.dtos;

public class ChangePasswordReq {

    private String email;
    private Integer otp;
    private String password;

    // Default constructor
    public ChangePasswordReq() {}

    // Constructor with all fields
    public ChangePasswordReq(String email, Integer otp, String password) {
        this.email = email;
        this.otp = otp;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
