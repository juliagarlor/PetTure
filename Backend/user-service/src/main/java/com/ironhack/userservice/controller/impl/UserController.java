package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.interfaces.*;
import com.ironhack.userservice.service.interfaces.*;
import com.ironhack.userservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController implements IUserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/user/search/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }

//    Get buddies
    @GetMapping("/user/buddies/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileDTO> getBuddies(@PathVariable String userName){
        return userService.getBuddies(userName);
    }

//    Get requests
    @GetMapping("/user/requests/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileDTO> getRequests(@PathVariable String userName){
        return userService.getRequests(userName);
    }

//    Get public users
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getPublicProfiles(){
        return userService.getPublicProfiles();
    }

//    Update profile pic
    @PutMapping("/user/profile-pic/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileDTO updateProfilePic(@PathVariable String userName, @RequestBody String profilePic){
        return userService.updateProfilePic(userName, profilePic);
    }

//    Add new buddy
    @PutMapping("/user/buddy/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addABuddy(@PathVariable String userName, @RequestBody String buddy){
        return userService.addABuddy(userName, buddy);
    }

//    Add new request
    @PutMapping("/user/request/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addRequest(@PathVariable String userName, @RequestBody String request){
        return userService.addRequest(userName, request);
    }

//    Remove user and return username
    @DeleteMapping("/user/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public String removeUser(@PathVariable String userName){
        return userService.removeUser(userName);
    }
}
