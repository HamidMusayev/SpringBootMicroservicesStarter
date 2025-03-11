package com.example.authservice.controller;

import com.example.authservice.dto.LoginRequest;
import com.example.authservice.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        String url = userServiceUrl + "/users/validate";
        Boolean isValid = restTemplate.postForObject(url, loginRequest, Boolean.class);

        if (Boolean.TRUE.equals(isValid)) {
            String token = jwtUtil.generateToken(loginRequest.getUsername());
            return "Bearer " + token;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }
}

