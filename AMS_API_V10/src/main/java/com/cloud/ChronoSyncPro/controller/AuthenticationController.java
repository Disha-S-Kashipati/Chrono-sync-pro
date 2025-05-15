package com.cloud.ChronoSyncPro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ChronoSyncPro.customExceptions.OTPExpiredException;
import com.cloud.ChronoSyncPro.dtos.AuthenticationRequest;
import com.cloud.ChronoSyncPro.dtos.AuthenticationResponse;
import com.cloud.ChronoSyncPro.dtos.ChangePasswordReq;
import com.cloud.ChronoSyncPro.dtos.ValidateOtp;
import com.cloud.ChronoSyncPro.entity.UserAuth;
import com.cloud.ChronoSyncPro.service.AuthenticationService;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest,
                                                               HttpServletResponse response) {
        log.info("Entered into Authenticate Method");
        return new ResponseEntity<>(
                authenticationService.authenticateUserAndCreateCookie(authenticationRequest, response), HttpStatus.OK);
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOTP(@RequestBody ValidateOtp validateOtp) {
        System.out.println(validateOtp);
        UserAuth userAuth = null;
        try {
            userAuth = authenticationService.checkValidity(validateOtp);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatusCode.valueOf(491));
        } catch (OTPExpiredException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatusCode.valueOf(492));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userAuth, HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changeOrUpdatePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        System.out.println(changePasswordReq);
        try {
            authenticationService.changePassword(changePasswordReq);
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatusCode.valueOf(491));
        } catch (OTPExpiredException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatusCode.valueOf(492));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatusCode.valueOf(493));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

