package com.app.ecom.userservice.controller;

import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> admin(){
        return ResponseEntity.status(HttpStatus.SC_OK).body("hello from user-service with Admin Role");
    }

    @GetMapping("/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> customer(){
        return ResponseEntity.status(HttpStatus.SC_OK).body("hello from user-service with Customer Role");
    }

}
