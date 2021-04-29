package com.ironhack.userservice.utils.dtos;

import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.*;
import java.util.*;

public class UserDTO {

    @NotEmpty(message = "Be creative, it's your username")
    @Length(max = 20, message = "SHORT NAMES!")
    private String userName;
    @NotEmpty(message = "Password is compulsory")
    private String password;
    @NotNull(message = "Pick your favourite pic")
    private Long profilePicture;
    @NotEmpty(message = "Please, enter a visibility value: public or private")
    private String visibility;
    private List<ProfileDTO> buddies;
    private List<ProfileDTO> requests;
    private String roles;

//    Constructors

    public UserDTO(@NotEmpty(message = "Be creative, it's your username") @Length(max = 20, message = "SHORT NAMES!")
                           String userName, @NotEmpty(message = "Password is compulsory") String password,
                   @NotNull(message = "Pick your favourite pic") Long profilePicture,
                   @NotEmpty(message = "Please, enter a visibility value: public or private") String visibility,
                   List<ProfileDTO> buddies, List<ProfileDTO> requests, String roles) {
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

    public Long getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Long profilePicture) {
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
