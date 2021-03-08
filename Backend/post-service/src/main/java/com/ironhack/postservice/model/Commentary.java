package com.ironhack.postservice.model;

import com.ironhack.postservice.utils.dtos.*;

import javax.persistence.*;

@Entity
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String body;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

//    Constructors

    public Commentary() {
    }

    public Commentary(String userName, String body, Post post) {
        this.userName = userName;
        this.body = body;
        this.post = post;
    }

//    Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
