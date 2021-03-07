package com.ironhack.pictureservice.utils.dtos;

import com.ironhack.pictureservice.model.*;

import javax.validation.constraints.*;

public class PictureDTO {

    private Long id;
    @NotEmpty(message = "A picture must have a name")
    private String pictureName;
    @NotEmpty(message = "A picture must belong to an user")
    private String userName;
    @Min(value = 0, message = "You are beautiful!")
    private int licks;

//    Constructors

    public PictureDTO(@NotEmpty(message = "A picture must have a name") String pictureName,
                      @NotEmpty(message = "A picture must belong to an user") String userName,
                      @Min(value = 0, message = "You are beautiful!") int licks) {
        this.pictureName = pictureName;
        this.userName = userName;
        this.licks = licks;
    }

    public PictureDTO(Picture picture) {
        this(picture.getPictureName(), picture.getUserName(), picture.getLicks());
        this.id = picture.getId();
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
