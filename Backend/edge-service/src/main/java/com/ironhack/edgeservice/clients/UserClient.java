package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.utils.dtos.*;
import com.ironhack.edgeservice.utils.payload.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient("user-service")
public interface UserClient {

//    Get user by username
    @GetMapping("/user/search/{userName}")
    UserDTO getUserByUserName(@PathVariable String userName);

//    Get basic profile by username
    @GetMapping("/user/basic-profile/{username}")
    ProfileDTO getProfileByUserName(@PathVariable String username);

//    Get buddies
    @GetMapping("/user/buddies/{userName}")
    List<ProfileDTO> getBuddies(@PathVariable String userName);

//    Get requests
    @GetMapping("/user/requests/{userName}")
    List<ProfileDTO> getRequests(@PathVariable String userName);

//    Get requested users
    @GetMapping("user/requested/{userName}")
    List<String> getRequested(@PathVariable String userName);

//    Get public users
    @GetMapping("/users")
    List<String> getPublicProfiles();

//    Update profile pic
    @PutMapping("/user/profile-pic/{userName}")
    ProfileDTO updateProfilePic(@PathVariable String userName, @RequestBody Long profilePic);

//    Add new buddy
    @PutMapping("/user/buddy/{userName}")
    UserDTO addABuddy(@PathVariable String userName, @RequestBody String buddy);

//    Add new request
    @PutMapping("/user/add/request/{userName}")
    UserDTO addRequest(@PathVariable String userName, @RequestBody String request);

//    Remove request
    @PutMapping("/user/remove/request/{userName}")
    UserDTO removeRequest(@PathVariable String userName, @RequestBody String request);

//    Remove user and return username
    @DeleteMapping("/user/{userName}")
    String removeUser(@PathVariable String userName);

//    Register
    @PostMapping("/user/auth/register")
    ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO);
}
