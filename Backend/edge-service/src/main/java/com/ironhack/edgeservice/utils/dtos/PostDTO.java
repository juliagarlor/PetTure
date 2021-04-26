package com.ironhack.edgeservice.utils.dtos;

import java.util.*;

public class PostDTO {

    private Long postId;
    private String postBody;
    private Long pictureId;
    private String userName;
    private int licks;
    private PictureDTO pic;

//    Getters and Setters

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
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

    public PictureDTO getPic() {
        return pic;
    }

    public void setPic(PictureDTO pic) {
        this.pic = pic;
    }
}
