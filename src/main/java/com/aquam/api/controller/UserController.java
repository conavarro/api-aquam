package com.aquam.api.controller;

import com.aquam.api.entity.User;
import com.aquam.api.service.UserService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping(value = "/users")
public class UserController extends ExceptionController{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<?> getUsers(){

        Map<String, Object> response = new HashMap<>();
        response.put("data", this.userService.getAllUsers());
        response.put("code", 0);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") String id){
        Map<String, Object> response = new HashMap<>();
        User user = this.userService.getUserById(Long.parseLong(id));
        response.put("code", 0);
        response.put("data", user);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user){
        this.userService.createUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("data", user);
        response.put("code", 0);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") String id){
        User user = this.userService.getUserById(Long.parseLong(id));
        this.userService.deleteUser(user);
        Map<String, Object> response = new HashMap<>();
        response.put("data", user);
        response.put("code", 0);
        return ResponseEntity.ok(response);
    }

    @PutMapping()
    public ResponseEntity<?> updateUsers(@RequestBody List<User> users){
        this.userService.updateUsers(users);
        Map<String, Object> response = new HashMap<>();
        response.put("data", users);
        response.put("code", 0);
        return ResponseEntity.ok(response);
    }

}
