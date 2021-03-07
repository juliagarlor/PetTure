package com.ironhack.pictureservice.model;

import com.ironhack.pictureservice.utils.dtos.*;

import javax.persistence.*;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pictureName;
    private String userName;
    private int licks;

//    Constructors

    public Picture() {
    }

//    When posting a new picture, it does not have licks yet
    public Picture(String pictureName, String userName) {
        this.pictureName = pictureName;
        this.userName = userName;
        this.licks = 0;
    }

    public Picture(PictureDTO pictureDTO) {
        this(pictureDTO.getPictureName(), pictureDTO.getUserName());
    }

//    Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLicks() {
        return licks;
    }

    public void setLicks(int licks) {
        this.licks = licks;
    }
}
