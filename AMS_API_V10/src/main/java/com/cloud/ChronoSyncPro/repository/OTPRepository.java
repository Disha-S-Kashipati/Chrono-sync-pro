package com.cloud.ChronoSyncPro.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.ChronoSyncPro.entity.OTP;
import com.cloud.ChronoSyncPro.entity.UserAuth;

public interface OTPRepository extends JpaRepository<OTP, Integer> {

	Optional<OTP> findByOtpCode(Integer otpCode);
	
	Optional<OTP> findByUserAuth(UserAuth userAuth);
	
	void deleteByUserAuth(UserAuth userAuth);
	
}
