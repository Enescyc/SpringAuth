package com.example.auth.controller;

import com.example.auth.dto.AuthRequestDto;
import com.example.auth.dto.CreateUserRequest;
import com.example.auth.model.User;
import com.example.auth.service.JwtService;
import com.example.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public User createUser(CreateUserRequest request) {
        return this.userService.createUser(request);
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequestDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(jwtService.generateToken(request.username()));
            } else return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }

    }
}
