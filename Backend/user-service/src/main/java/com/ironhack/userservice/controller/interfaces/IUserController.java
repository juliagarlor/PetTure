package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.utils.dtos.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

public interface IUserController {
    UserDTO getUserByUserName(String userName);
    ProfileDTO getProfileByUserName(String username);
    List<ProfileDTO> getBuddies(String userName);
    List<ProfileDTO> getRequests(String userName);
    List<String> getRequested(String userName);
    List<String> getPublicProfiles();
    UserDTO registerUser(UserDTO userDTO);
    ProfileDTO updateProfilePic(String userName, String profilePic);
    UserDTO addABuddy(String userName, String buddy);
    UserDTO addRequest(String userName, String request);
    UserDTO removeRequest(String userName, String request);
    Long removeUser(String userName);
}
