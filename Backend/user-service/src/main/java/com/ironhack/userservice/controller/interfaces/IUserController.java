package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.utils.dtos.*;

import java.util.*;

public interface IUserController {
    UserDTO getUserByUserName(String userName);
    List<ProfileDTO> getBuddies(String userName);
    List<ProfileDTO> getRequests(String userName);
    List<String> getPublicProfiles();
    UserDTO newUser(UserDTO userDTO);
    ProfileDTO updateProfilePic(String userName, String profilePic);
    UserDTO addABuddy(String userName, String buddy);
    UserDTO addRequest(String userName, String request);
    String removeUser(String userName);
}
