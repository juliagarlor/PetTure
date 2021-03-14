package com.ironhack.edgeservice.utils.dtos;

import javax.validation.constraints.*;

public class PictureDTO {

    private Long picId;
    private String pictureName;
    private String type;
    private byte[] pic;

//    Getters and Setters
    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
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
