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

    public List<String> getPublicProfiles() {
        return userRepository.findPublicUserNames(Visibility.PUBLIC);
    }

    public UserDTO newUser(UserDTO userDTO) {

        try {
            Visibility.valueOf(userDTO.getVisibility().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Introduce a valid visibility value.");
        }

        User newUser = new User(userDTO);
        userRepository.save(newUser);

        return userDTO;
    }

    public ProfileDTO updateProfilePic(String userName, String profilePic) {
        User userToUpdate = checkUserName(userName);
        userToUpdate.setProfilePicture(profilePic);
        userRepository.save(userToUpdate);

        return new ProfileDTO(userToUpdate);
    }

    public UserDTO addABuddy(String userName, String buddy) {
        System.out.println("Looking for " + userName);
        User userToUpdate = checkUserName(userName);
        System.out.println("Looking for " + buddy);
        User newBuddy = checkUserName(buddy);

        System.out.println("Both found!");
//        Updating the buddies List of the current user
        List<User> buddies = userToUpdate.getBuddies();
        buddies.add(newBuddy);
        userToUpdate.setBuddies(buddies);
        System.out.println("Added buddy to current user");

//        And the buddy's buddy list
        buddies = newBuddy.getBuddies();
        buddies.add(userToUpdate);
        newBuddy.setBuddies(buddies);
        System.out.println("Added buddy to buddy");

//        And saving
        userRepository.saveAll(List.of(userToUpdate, newBuddy));

        return buildUserDTO(userToUpdate);
    }

    public UserDTO addRequest(String userName, String request) {
        User userToUpdate = checkUserName(userName);
        User newRequest = checkUserName(request);

        List<User> requests = userToUpdate.getRequests();
        requests.add(newRequest);
        userToUpdate.setRequests(requests);
        userRepository.save(userToUpdate);

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
                buddies, requests);
    }
}
