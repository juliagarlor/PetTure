package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.interfaces.*;
import com.ironhack.userservice.model.*;
import com.ironhack.userservice.service.interfaces.*;
import com.ironhack.userservice.utils.dtos.*;
import com.ironhack.userservice.utils.enums.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import javax.validation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
public class UserController implements IUserController {

    @Autowired
    private IUserService userService;

//    Get user by username
    @GetMapping("/user/search/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserByUserName(@PathVariable String userName) {
        return userService.getUserByUserName(userName);
    }

//    Get basic profile by username
    @GetMapping("/user/basic-profile/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ProfileDTO getProfileByUserName(@PathVariable String username){
        return userService.getProfileByUserName(username);
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

//    Registering a new user
    @PostMapping("/user/auth/register")
    public UserDTO registerUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
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
    @PutMapping("/user/add/request/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addRequest(@PathVariable String userName, @RequestBody String request){
        return userService.addRequest(userName, request);
    }

//    Remove request
    @PutMapping("/user/remove/request/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO removeRequest(@PathVariable String userName, @RequestBody String request){
        return userService.removeRequest(userName, request);
    }

//    Remove user and return username
    @DeleteMapping("/user/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public String removeUser(@PathVariable String userName){
        return userService.removeUser(userName);
    }
}
