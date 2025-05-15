package com.cloud.ChronoSyncPro.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.ChronoSyncPro.entity.RefreshToken;
import com.cloud.ChronoSyncPro.entity.UserAuth;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
	
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
	
	Optional<RefreshToken> findByUserAuth(UserAuth userAuth);
	
	void deleteByRefreshToken(String refreshToken);
	
}