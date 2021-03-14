package com.ironhack.edgeservice.utils.dtos;

import java.util.*;

public class UserDTO {

    private String userName;
    private String password;
    private Long profilePicture;
    private String visibility;
    private List<ProfileDTO> buddies;
    private int buddyNum;
    private List<ProfileDTO> requests;
    private List<PostDTO> posts;
    private RoleDTO role;

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

    public int getBuddyNum() {
        return buddyNum;
    }

    public void setBuddyNum(int buddyNum) {
        this.buddyNum = buddyNum;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
