package com.example.auth_service.controller;

import com.example.auth_service.entity.UserCredentialsEntity;
import com.example.auth_service.service.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserCredentialsController {

    @Autowired
    private UserCredentialsService userCredService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public UserCredentialsEntity register(@RequestBody UserCredentialsEntity user) {
        return userCredService.register(user);
    }

    @GetMapping("/validate/token")
    public boolean validateToken(@RequestParam String token) {
        return userCredService.verifyToken(token);
    }

    @PostMapping("/validate/user")
    public String getToken(@RequestBody UserCredentialsEntity user) {
        Authentication authentication = authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword())));
        if(authentication.isAuthenticated()){
            return userCredService.generateToken(user.getName());
        }
        return null;
    }
}