package com.ironhack.userservice.service.interfaces;

import com.ironhack.userservice.utils.dtos.*;

import java.util.*;

public interface IUserService {
    UserDTO getUserByUserName(String userName);
    ProfileDTO getProfileByUserName(String username);
    List<ProfileDTO> getBuddies(String userName);
    List<ProfileDTO> getRequests(String userName);
    List<String> getPublicProfiles();
    UserDTO registerUser(UserDTO userDTO);
    ProfileDTO updateProfilePic(String userName, String profilePic);
    UserDTO addABuddy(String userName, String buddy);
    UserDTO addRequest(String userName, String request);
    UserDTO removeRequest(String userName, String request);
    String removeUser(String userName);
}
