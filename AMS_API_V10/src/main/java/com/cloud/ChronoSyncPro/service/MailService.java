
package com.cloud.ChronoSyncPro.service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cloud.ChronoSyncPro.entity.OTP;
import com.cloud.ChronoSyncPro.entity.UserAuth;
import com.cloud.ChronoSyncPro.repository.OTPRepository;
import com.cloud.ChronoSyncPro.repository.UserAuthRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    private final UserAuthRepository authRepository;
    private final OTPRepository otpRepository;

    // Constructor
    public MailService(JavaMailSender javaMailSender, UserAuthRepository authRepository, OTPRepository otpRepository) {
        this.javaMailSender = javaMailSender;
        this.authRepository = authRepository;
        this.otpRepository = otpRepository;
    }

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${otp.expiration_time}")
    private String otpExpirationTime;

    public void sendMail(String email) throws UsernameNotFoundException, MailException {

        UserAuth userAuth = authRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        Optional<OTP> findByUserAuth = otpRepository.findByUserAuth(userAuth);
        if (findByUserAuth.isPresent()) {
            otpRepository.delete(findByUserAuth.get());
        }

        int otp = new Random().nextInt(900000) + 100000;

        OTP otpEntity = new OTP();
        otpEntity.setUserAuth(userAuth);
        otpEntity.setOtpCode(otp);
        otpEntity.setExpirationTime(new Date(System.currentTimeMillis() + Integer.parseInt(otpExpirationTime)));

        otpRepository.save(otpEntity);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText("Your One Time Password is :: " + otp + "\nValid for 5 minutes");
        simpleMailMessage.setSubject("OTP");
        javaMailSender.send(simpleMailMessage);
    }
}
