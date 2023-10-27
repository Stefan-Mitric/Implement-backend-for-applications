package com.example.demo.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/registration")
    public ResponseEntity<String> register(@RequestBody RegistrationDto registrationDto) {
        authService.register(registrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.getJwt(loginDto));
    }
}
