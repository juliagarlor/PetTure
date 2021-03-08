package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.utils.dtos.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/user/{userName}")
    @ResponseStatus(HttpStatus.OK)
    UserDTO getUserByUserName(@PathVariable String userName);

//    Get buddies
    @GetMapping("/user/{userName}/buddies")
    List<ProfileDTO> getBuddies(@PathVariable String userName);

//    Get requests
    @GetMapping("/user/{userName}/requests")
    List<ProfileDTO> getRequests(@PathVariable String userName);

//    Get public users
    @GetMapping("/user")
    List<String> getPublicProfiles();

//    Register user
    @PostMapping("/user")
    UserDTO newUser(@RequestBody UserDTO userDTO);

//    Update profile pic
    @PutMapping("/user/{userName}/profile-pic")
    ProfileDTO updateProfilePic(@PathVariable String userName, @RequestBody String profilePic);

//    Add new buddy
    @PutMapping("/user/{userName}/buddy")
    UserDTO addABuddy(@PathVariable String userName, @RequestBody String buddy);

//    Add new request
    @PutMapping("/user/{userName}/request")
    UserDTO addRequest(@PathVariable String userName, @RequestBody String request);

//    Remove user and return username
    @DeleteMapping("/user/{userName}")
    String removeUser(@PathVariable String userName);
}
