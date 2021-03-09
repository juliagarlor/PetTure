package com.ironhack.userservice.model;

import com.ironhack.userservice.utils.enums.*;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

//    Constructors
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

//    Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
