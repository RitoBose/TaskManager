// AuthController.java
package com.intern.taskM.controller;

import com.intern.taskM.model.User;
import com.intern.taskM.service.UserService;
import com.intern.taskM.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
//    	System.out.println("ooooooooooooooo");
        User user = userService.findByUsername(loginRequest.getUsername());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return jwtUtil.generateToken(user);
        }
        throw new RuntimeException("Invalid credentials");
    }
    
 // New registration endpoint
    @PostMapping("/register")
    public User register(@RequestBody User newUser) {

        return userService.save(newUser);
    }
}
