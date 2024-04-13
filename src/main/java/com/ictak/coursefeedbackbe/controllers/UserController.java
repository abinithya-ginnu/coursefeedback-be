package com.ictak.coursefeedbackbe.controllers;

import com.ictak.coursefeedbackbe.models.Users;
import com.ictak.coursefeedbackbe.repository.UserRepository;
import com.ictak.coursefeedbackbe.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Users user) {
        Map<String, Object> response = new HashMap<>();
        try{
            Users userData = userRepository.login(user.getEmail(), user.getPassword());
            if (userData != null) {
                // Token Generated
                response.put("status", "success");
                response.put("code", 200);
                response.put("userId", userData.getId());
                response.put("name", userData.getName());
                response.put("token", JwtUtils.generateToken(userData.getEmail()));
            } else {
                response.put("code", "404");
            }
        } catch (Exception e){
            response.put("status", "error");
            response.put("code", 500);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
}
