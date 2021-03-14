package com.ironhack.pictureservice.model;

import com.ironhack.pictureservice.utils.dtos.*;

import javax.persistence.*;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pictureName;
    private String type;
    @Column(name = "pic", length = 10000)
    private byte[] pic;

//    Constructors

    public Picture() {
    }

    public Picture(String pictureName, String type, byte[] pic) {
        this.pictureName = pictureName;
        this.type = type;
        this.pic = pic;
    }

    public Picture(PictureDTO pictureDTO) {
        this(pictureDTO.getPictureName(), pictureDTO.getType(), pictureDTO.getPic());
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }
}
