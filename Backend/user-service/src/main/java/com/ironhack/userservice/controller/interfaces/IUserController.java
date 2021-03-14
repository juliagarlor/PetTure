package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.utils.dtos.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface IUserController {
    UserDTO getUserByUserName(String userName);
    ProfileDTO getProfileByUserName(String username);
    List<ProfileDTO> getBuddies(String userName);
    List<ProfileDTO> getRequests(String userName);
    List<String> getPublicProfiles();
    ProfileDTO updateProfilePic(String userName, Long profilePic);
    UserDTO addABuddy(String userName, String buddy);
    UserDTO addRequest(String userName, String request);
    UserDTO removeRequest(String userName, String request);
    String removeUser(String userName);
}
