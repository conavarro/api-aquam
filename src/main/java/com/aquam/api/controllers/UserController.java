package com.aquam.api.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @GetMapping("/")
    public ResponseEntity<?> getUsers(){
        System.out.println(dbUrl);
        return ResponseEntity.ok("hola mundo!");
    }
}
