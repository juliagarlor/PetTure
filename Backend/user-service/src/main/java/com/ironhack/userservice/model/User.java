package com.ironhack.userservice.model;

import com.ironhack.userservice.utils.dtos.*;
import com.ironhack.userservice.utils.enums.*;
import org.hibernate.annotations.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.*;

@Entity
public class User {

    @Id
    private String userName;
    private String password;
    private String profilePicture;
    @Enumerated(EnumType.STRING)
    private Visibility visibility;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "user_buddies",
            joinColumns = { @JoinColumn(name = "`current_user`") },
            inverseJoinColumns = { @JoinColumn(name = "buddy") }
    )
    private List<User> buddies;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "request_to_user",
            joinColumns = { @JoinColumn(name = "requested_user") },
            inverseJoinColumns = { @JoinColumn(name = "requesting_user") }
    )
    private List<User> requests;
//    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
    private String role;
    @Transient
    protected PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    Constructors
    public User() {
    }

    public User(String userName, String password, String profilePicture, Visibility visibility) {
        this.userName = userName;
        this.password = passwordEncoder.encode(password);
        this.profilePicture = profilePicture;
        this.visibility = visibility;
        this.buddies = new ArrayList<>();
        this.requests = new ArrayList<>();
        this.role = "USER";
    }

    public User(UserDTO userDTO){
        this(userDTO.getUserName(), userDTO.getPassword(), userDTO.getProfilePicture(), Visibility.valueOf(userDTO.getVisibility()));
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

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public List<User> getBuddies() {
        return buddies;
    }

    public void setBuddies(List<User> buddies) {
        this.buddies = buddies;
    }

    public List<User> getRequests() {
        return requests;
    }

    public void setRequests(List<User> requests) {
        this.requests = requests;
    }

    public String getRoles() {
        return role;
    }

    public void setRoles(String roles) {
        this.role = roles;
    }
}
