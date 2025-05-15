package com.cloud.ChronoSyncPro.service;

import java.util.Date;


import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloud.ChronoSyncPro.config.JwtUtils;
import com.cloud.ChronoSyncPro.customExceptions.OTPExpiredException;
import com.cloud.ChronoSyncPro.dtos.AuthenticationRequest;
import com.cloud.ChronoSyncPro.dtos.AuthenticationResponse;
import com.cloud.ChronoSyncPro.dtos.ChangePasswordReq;
import com.cloud.ChronoSyncPro.dtos.ValidateOtp;
import com.cloud.ChronoSyncPro.entity.OTP;
import com.cloud.ChronoSyncPro.entity.RefreshToken;
import com.cloud.ChronoSyncPro.entity.UserAuth;
import com.cloud.ChronoSyncPro.repository.OTPRepository;
import com.cloud.ChronoSyncPro.repository.UserAuthRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class AuthenticationService {
    
    private final AuthenticationManager authenticationManager;
    private final UserAuthService userAuthService;
    private final UserAuthRepository authRepository;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    private final CookieService cookieService;
    private final OTPRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    
    public AuthenticationService(AuthenticationManager authenticationManager, UserAuthService userAuthService, 
                                 UserAuthRepository authRepository, JwtUtils jwtUtils, 
                                 RefreshTokenService refreshTokenService, CookieService cookieService, 
                                 OTPRepository otpRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userAuthService = userAuthService;
        this.authRepository = authRepository;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
        this.cookieService = cookieService;
        this.otpRepository = otpRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthenticationResponse authenticateUserAndCreateCookie(AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        AuthenticationResponse authResponse = null;
        if (authentication.isAuthenticated()) {
            UserAuth userAuth = userAuthService.findByEmail(authenticationRequest.getEmail());
            
            if (userAuth == null) {
                throw new UsernameNotFoundException("User not found");
            }
            
            Optional<OTP> otpOfUser = otpRepository.findByUserAuth(userAuth);
            
            if (otpOfUser.isPresent()) {
                otpRepository.delete(otpOfUser.get());
            }
            
            UserDetails userDetails = toUserDetails(userAuth);
            String token = jwtUtils.generateToken(userDetails);
            
            authResponse = new AuthenticationResponse(token, userAuth.getRole().toString(), authenticationRequest.getEmail());
            
            String refreshToken = UUID.randomUUID().toString();
            
            RefreshToken refreshTokenByUser = refreshTokenService.getRefreshTokenByUser(userAuth);
            
            if (refreshTokenByUser != null) {
                refreshTokenService.deleteRefreshTokenById(refreshTokenByUser.getId());
            }
            
            refreshTokenService.saveRefreshToken(userAuth, refreshToken);
            
            ResponseCookie resCookie = cookieService.createCookie(refreshToken);
            response.addHeader("Set-Cookie", resCookie.toString());
        }
        return authResponse;
    }

    @Transactional
    public UserAuth checkValidity(ValidateOtp validateOtp) throws UsernameNotFoundException, OTPExpiredException {
        UserAuth userAuth = userAuthService.findByEmail(validateOtp.getEmail());

        if (userAuth == null) {
            throw new UsernameNotFoundException("User not found");
        }

        OTP otp = otpRepository.findByOtpCode(validateOtp.getOtp())
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));
        
        if (otp.getExpirationTime().before(new Date(System.currentTimeMillis()))) {
            otpRepository.delete(otp);
            throw new OTPExpiredException("OTP Expired");
        }
        
        return userAuth;
    }

    public void changePassword(ChangePasswordReq changePasswordReq) throws UsernameNotFoundException, OTPExpiredException {
        UserAuth userAuth = userAuthService.findByEmail(changePasswordReq.getEmail());

        if (userAuth == null) {
            throw new UsernameNotFoundException("User not found");
        }
        
        OTP otp = otpRepository.findByOtpCode(changePasswordReq.getOtp())
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (otp.getExpirationTime().before(new Date(System.currentTimeMillis()))) {
            otpRepository.delete(otp);
            throw new OTPExpiredException("OTP Expired");
        }

        if (otp.getUserAuth().getId().equals(userAuth.getId())) {
            userAuth.setPassword(passwordEncoder.encode(changePasswordReq.getPassword()));
            authRepository.save(userAuth);
        }
        
        otpRepository.delete(otp);
    }

    private UserDetails toUserDetails(UserAuth userAuth) {
        return User.withUsername(userAuth.getEmail())
                .password(userAuth.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority(userAuth.getRole().toString())))
                .build();
    }
}
