package com.ironhack.userservice.service.interfaces;

import com.ironhack.userservice.utils.dtos.*;

import java.util.*;

public interface IUserService {
    UserDTO getUserByUserName(String userName);
    List<ProfileDTO> getBuddies(String userName);
    List<ProfileDTO> getRequests(String userName);
    List<String> getPublicProfiles();
    ProfileDTO updateProfilePic(String userName, String profilePic);
    UserDTO addABuddy(String userName, String buddy);
    UserDTO addRequest(String userName, String request);
    String removeUser(String userName);
}
