package com.ironhack.pictureservice.utils.dtos;

import com.ironhack.pictureservice.model.*;

import javax.validation.constraints.*;

public class PictureDTO {

    private Long picId;
    @NotEmpty(message = "A picture must have a name")
    private String pictureName;
    private String type;
    private byte[] pic;

//    Constructors
    public PictureDTO(@NotEmpty(message = "A picture must have a name") String pictureName, String type, byte[] pic) {
        this.pictureName = pictureName;
        this.type = type;
        this.pic = pic;
    }

    public PictureDTO(Picture picture) {
        this(picture.getPictureName(), picture.getType(), picture.getPic());
        this.picId = picture.getId();
    }

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
