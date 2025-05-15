package com.cloud.ChronoSyncPro.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "otp")
public class OTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserAuth userAuth;

    @Column(nullable = false, unique = true)
    private Integer otpCode;

    @Column(nullable = false)
    private Date expirationTime;

    // Default constructor
    public OTP() {}

    // Constructor with all fields
    public OTP(Integer id, UserAuth userAuth, Integer otpCode, Date expirationTime) {
        this.id = id;
        this.userAuth = userAuth;
        this.otpCode = otpCode;
        this.expirationTime = expirationTime;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }

    public Integer getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(Integer otpCode) {
        this.otpCode = otpCode;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    // equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OTP otp = (OTP) o;
        return id != null && id.equals(otp.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "OTP{" +
                "id=" + id +
                ", userAuth=" + userAuth +
                ", otpCode=" + otpCode +
                ", expirationTime=" + expirationTime +
                '}';
    }
}
