package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.utils.dtos.*;
import com.ironhack.edgeservice.utils.payload.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/user/search/{userName}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO getUserByUserName(@PathVariable String userName);

//    Get buddies
    @GetMapping("/user/buddies/{userName}")
    List<ProfileDTO> getBuddies(@PathVariable String userName);

//    Get requests
    @GetMapping("/user/requests/{userName}")
    List<ProfileDTO> getRequests(@PathVariable String userName);

//    Get public users
    @GetMapping("/users")
    List<String> getPublicProfiles();

//    Update profile pic
    @PutMapping("/user/profile-pic/{userName}")
    ProfileDTO updateProfilePic(@PathVariable String userName, @RequestBody String profilePic);

//    Add new buddy
    @PutMapping("/user/buddy/{userName}")
    UserDTO addABuddy(@PathVariable String userName, @RequestBody String buddy);

//    Add new request
    @PutMapping("/user/request/{userName}")
    UserDTO addRequest(@PathVariable String userName, @RequestBody String request);

//    Remove user and return username
    @DeleteMapping("/user/{userName}")
    String removeUser(@PathVariable String userName);

//    Login
    @PostMapping("/user/auth/login")
    ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest);

    @PostMapping("/user/auth/register")
    ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO);
}
