package com.ironhack.edgeservice.utils.dtos;

import java.util.*;

public class UserDTO {

    private String userName;
    private String password;
    private String profilePicture;
    private String visibility;
    private List<ProfileDTO> buddies;
    private List<ProfileDTO> requests;

//    Getters and Setters

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public List<ProfileDTO> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<ProfileDTO> buddies) {
        this.buddies = buddies;
    }

    public List<ProfileDTO> getRequests() {
        return requests;
    }

    public void setRequests(List<ProfileDTO> requests) {
        this.requests = requests;
    }
}
