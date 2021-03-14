package com.ironhack.postservice.model;

import com.ironhack.postservice.utils.dtos.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private Long pictureId;
    private String userName;
    private int licks;

//    Constructors

    public Post() {
    }

    public Post(String body, Long pictureId, String userName) {
        this.body = body;
        this.pictureId = pictureId;
        this.licks = 0;
    }

    public Post(PostDTO postDTO){
        this(postDTO.getPostBody(), postDTO.getPictureId(), postDTO.getUserName());
    }

//    Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
}
