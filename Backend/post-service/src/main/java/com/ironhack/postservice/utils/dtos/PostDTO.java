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

//    Constructors
    public PostDTO(@Length(max = 100, message = "Please, leave space for the rest") String body,
                   @NotNull(message = "A post must be associated to an image, this is not twitter") Long pictureId) {
        this.postBody = body;
        this.pictureId = pictureId;
    }

    public PostDTO(Post post) {
        this(post.getBody(), post.getPictureId());
        this.postId = post.getId();
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
}
