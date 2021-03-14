package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.model.*;
import com.ironhack.userservice.repository.*;
import com.ironhack.userservice.utils.dtos.*;
import com.ironhack.userservice.utils.enums.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import javax.validation.*;

@RestController
@RequestMapping("/user/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
        if (userRepository.findById(userDTO.getUserName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken");
        }

        try {
            Visibility.valueOf(userDTO.getVisibility().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Introduce a valid visibility value.");
        }

        // Create new user's account
        User user = new User(userDTO);

        userRepository.save(user);

        return userDTO;
    }
}
