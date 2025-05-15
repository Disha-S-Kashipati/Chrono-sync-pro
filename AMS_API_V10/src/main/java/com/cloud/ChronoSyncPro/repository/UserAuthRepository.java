package com.cloud.ChronoSyncPro.repository;

import java.util.Optional;




import org.springframework.data.jpa.repository.JpaRepository;

import com.cloud.ChronoSyncPro.entity.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer>{

	Optional<UserAuth> findByEmail(String email);

}