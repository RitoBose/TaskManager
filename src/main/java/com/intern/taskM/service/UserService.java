// UserService.java
package com.intern.taskM.service;

import com.intern.taskM.model.User;
import com.intern.taskM.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    
 // Registration: save a new user
    public User save(User user) {
        // In a real application, hash the password.
        return userRepository.save(user);
    }
}
