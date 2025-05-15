package com.cloud.ChronoSyncPro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ChronoSyncPro.service.MailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200/", allowedHeaders = "*", allowCredentials = "true")
public class EmailController {
    
    private final MailService mailService;
    private static final Logger log = LoggerFactory.getLogger(EmailController.class);

    public EmailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/send-otp")
    public ResponseEntity<?> checkEmail(@RequestParam String email) {
        log.info(email);
        try {
            mailService.sendMail(email);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
