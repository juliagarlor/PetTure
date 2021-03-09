package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.model.*;
import com.ironhack.userservice.payload.request.*;
import com.ironhack.userservice.payload.response.*;
import com.ironhack.userservice.repository.*;
import com.ironhack.userservice.security.jwt.*;
import com.ironhack.userservice.security.services.*;
import com.ironhack.userservice.utils.dtos.*;
import com.ironhack.userservice.utils.enums.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;
import java.util.stream.*;

@RestController
@RequestMapping("/user/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        if (userRepository.findById(userDTO.getUserName()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        try {
            Visibility.valueOf(userDTO.getVisibility().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Introduce a valid visibility value.");
        }

        // Create new user's account
        User user = new User(userDTO);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
