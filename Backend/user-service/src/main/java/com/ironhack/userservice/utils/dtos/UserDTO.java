package com.ironhack.userservice.utils.dtos;

import com.ironhack.userservice.model.*;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotEmpty;
import java.util.*;

public class UserDTO {

    @NotEmpty(message = "Be creative, it's your username")
    @Length(max = 20, message = "SHORT NAMES!")
    private String userName;
    @NotEmpty(message = "Password is compulsory")
    private String password;
    @NotEmpty(message = "Pick your favourite pic")
    private String profilePicture;
    private String visibility;
    private List<ProfileDTO> buddies;
    private List<ProfileDTO> requests;
    private Role roles;

//    Constructors
    public UserDTO(@NotEmpty(message = "Be creative, it's your username") @Length(max = 10, message = "SHORT NAMES!")
                           String userName, @NotEmpty(message = "Password is compulsory") String password,
                   @NotEmpty(message = "Pick your favourite pic") String profilePicture, String visibility,
                   List<ProfileDTO> buddies, List<ProfileDTO> requests, Role roles) {
        this.userName = userName;
        this.password = password;
        this.profilePicture = profilePicture;
        this.visibility = visibility;
        this.buddies = buddies;
        this.requests = requests;
        this.roles = roles;
    }

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

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }
}
