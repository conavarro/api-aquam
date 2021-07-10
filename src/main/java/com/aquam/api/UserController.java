package com.aquam.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok("hola mundo!");
    }
}
