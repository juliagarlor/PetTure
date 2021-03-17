package com.ironhack.postservice.utils.dtos;

import com.ironhack.postservice.model.*;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

public class PostDTO {

    private Long postId;
    @Length(max = 100, message = "Please, leave space for the rest")
    private String postBody;
    @NotNull(message = "A post must be associated to an image, this is not twitter")
    private Long pictureId;
    private String userName;
    private int licks;

//    Constructors
    public PostDTO(@Length(max = 100, message = "Please, leave space for the rest") String body,
                   @NotNull(message = "A post must be associated to an image, this is not twitter") Long pictureId, String userName) {
        this.postBody = body;
        this.pictureId = pictureId;
        this.userName = userName;
    }

    public PostDTO(Post post) {
        this(post.getBody(), post.getPictureId(), post.getUserName());
        this.postId = post.getId();
        this.licks = post.getLicks();
    }

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
}
