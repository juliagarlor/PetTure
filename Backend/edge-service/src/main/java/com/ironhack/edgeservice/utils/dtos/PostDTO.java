package com.ironhack.edgeservice.utils.dtos;

import java.util.*;

public class PostDTO {

    private Long postId;
    private String postBody;
    private Long pictureId;
    private PictureDTO picture;
    private List<CommentaryDTO> commentaries;

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

    public PictureDTO getPicture() {
        return picture;
    }

    public void setPicture(PictureDTO picture) {
        this.picture = picture;
    }

    public List<CommentaryDTO> getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(List<CommentaryDTO> commentaries) {
        this.commentaries = commentaries;
    }
}
