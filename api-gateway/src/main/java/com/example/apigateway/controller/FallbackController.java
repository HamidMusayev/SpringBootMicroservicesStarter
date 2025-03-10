package com.example.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/product")
    public String productFallback() {
        return "Product service is down. Please try again later.";
    }

    @GetMapping("/user")
    public String userFallback() {
        return "User service is down. Please try again later.";
    }
}
