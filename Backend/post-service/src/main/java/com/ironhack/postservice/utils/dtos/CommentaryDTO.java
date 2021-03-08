package com.ironhack.postservice.utils.dtos;

import com.ironhack.postservice.model.*;
import org.hibernate.validator.constraints.*;

import javax.validation.constraints.NotEmpty;

public class CommentaryDTO {

    private Long commentaryId;
    @NotEmpty(message = "Be brave")
    private String userName;
    @NotEmpty(message = "Say something to your friend")
    @Length(max = 100, message = "Please, leave space for the rest")
    private String commentBody;
    private Long postId;

//    Constructors
    public CommentaryDTO(@NotEmpty(message = "Be brave") String userName, @NotEmpty(message = "Say something to your friend")
    @Length(max = 100, message = "Please, leave space for the rest") String body, Long postId) {
        this.userName = userName;
        this.commentBody = body;
        this.postId = postId;
    }

    public CommentaryDTO(Commentary commentary) {
        this(commentary.getUserName(), commentary.getBody(), commentary.getPost().getId());
        this.commentaryId = commentary.getId();
    }

//    Getters and Setters
    public Long getCommentaryId() {
        return commentaryId;
    }

    public void setCommentaryId(Long commentaryId) {
        this.commentaryId = commentaryId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
