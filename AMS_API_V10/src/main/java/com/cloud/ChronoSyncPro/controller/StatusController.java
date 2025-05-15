package com.cloud.ChronoSyncPro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/status")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
public class StatusController {

    private static final Logger log = LoggerFactory.getLogger(StatusController.class);

    public StatusController() {
    }

    @GetMapping("/isLoggedIn")
    public ResponseEntity<Boolean> isLoggedIn() {
        log.info("USER Authenticated");
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
