package com.example.auth.controller;

import com.example.auth.dto.CreateUserRequest;
import com.example.auth.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;



    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
    }


    @GetMapping("/hello")

    public String hello() {
        System.out.println("hello");
        try {
            return "hello";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }
    @PostMapping("/createUser")
    public UserDetails createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}
