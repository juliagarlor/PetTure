package com.ironhack.userservice.model;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    private String userName;
    private String name;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_name")
    private User user;

//    Constructors
    public Role() {
    }

    public Role(String userName, String name) {
        this.userName = userName;
        this.name = name;
    }

    //    Getters and Setters


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
