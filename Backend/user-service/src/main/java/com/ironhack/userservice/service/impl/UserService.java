package com.ironhack.userservice.service.impl;

import com.ironhack.userservice.model.*;
import com.ironhack.userservice.repository.*;
import com.ironhack.userservice.service.interfaces.*;
import com.ironhack.userservice.utils.dtos.*;
import com.ironhack.userservice.utils.enums.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO getUserByUserName(String userName) {
        User user = checkUserName(userName);
        return buildUserDTO(user);
    }

    public ProfileDTO getProfileByUserName(String username) {
        User user = checkUserName(username);
        return new ProfileDTO(user);
    }

    public List<ProfileDTO> getBuddies(String userName) {
        User user = checkUserName(userName);
        List<User> buddies = user.getBuddies();

        return buildProfiles(buddies);
    }

    public List<ProfileDTO> getRequests(String userName) {
        User user = checkUserName(userName);
        List<User> requests = user.getRequests();

        return buildProfiles(requests);
    }

    public List<String> getRequested(String userName) {
        User user = checkUserName(userName);
        return userRepository.findRequestedUsers(userName);
    }

    public List<String> getPublicProfiles() {
        return userRepository.findPublicUserNames(Visibility.PUBLIC);
    }

    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.findById(userDTO.getUserName()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken");
        }

        try {
            Visibility.valueOf(userDTO.getVisibility().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Introduce a valid visibility value.");
        }

        // Create new user's account
        User user = new User(userDTO);

        userRepository.save(user);

        return userDTO;
    }

    public ProfileDTO updateProfilePic(String userName, String profilePic) {
        Long profilePicId = 0L;
        try {
            profilePicId = Long.parseLong(profilePic);
        }catch (NumberFormatException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The profile picture id must be a number");
        }
        User userToUpdate = checkUserName(userName);
        userToUpdate.setProfilePicture(profilePicId);
        userRepository.save(userToUpdate);

        return new ProfileDTO(userToUpdate);
    }

    public UserDTO addABuddy(String userName, String buddy) {
        User userToUpdate = checkUserName(userName);
        User newBuddy = checkUserName(buddy);

//        Updating the requests list
        for (User request : userToUpdate.getRequests()){
            if (request.equals(newBuddy)){
                removeRequest(userName, buddy);
                break;
            }
        }
//        Updating the buddies List of the current user
        List<User> buddies = userToUpdate.getBuddies();
        buddies.add(newBuddy);
        userToUpdate.setBuddies(buddies);

//        And the buddy's buddy list
        buddies = newBuddy.getBuddies();
        buddies.add(userToUpdate);
        newBuddy.setBuddies(buddies);

//        And saving
        userRepository.saveAll(List.of(userToUpdate, newBuddy));

        return buildUserDTO(userToUpdate);
    }

    public UserDTO addRequest(String userName, String request) {
        User userToUpdate = checkUserName(userName);
        User newRequest = checkUserName(request);

        List<User> requests = userToUpdate.getRequests();
        if (!requests.contains(newRequest)){
            requests.add(newRequest);
            userToUpdate.setRequests(requests);
            userRepository.save(userToUpdate);
        }
        return buildUserDTO(userToUpdate);
    }

    public UserDTO removeRequest(String userName, String request) {
        User userToUpdate = checkUserName(userName);
        User oldRequest = checkUserName(request);

        List<User> requests = userToUpdate.getRequests();
        if (requests.contains(oldRequest)){
            requests.remove(oldRequest);
            userToUpdate.setRequests(requests);
            userRepository.save(userToUpdate);
        }

        return buildUserDTO(userToUpdate);
    }

    public String removeUser(String userName) {
        User userToRemove = checkUserName(userName);
        userRepository.delete(userToRemove);
        return userName;
    }

    public User checkUserName(String userName){
        Optional<User> output = userRepository.findById(userName);

        if (output.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an user with such an username");
        }

        return output.get();
    }

    public List<ProfileDTO> buildProfiles(List<User> users){
        List<ProfileDTO> output = new ArrayList<>();

        for (User u : users){
            output.add(new ProfileDTO(u));
        }
        return output;
    }

    public UserDTO buildUserDTO(User user){
        List<ProfileDTO> buddies = buildProfiles(user.getBuddies());
        List<ProfileDTO> requests = buildProfiles(user.getRequests());

        return new UserDTO(user.getUserName(), user.getPassword(), user.getProfilePicture(), user.getVisibility().toString(),
                buddies, requests, user.getRoles());
    }
}
