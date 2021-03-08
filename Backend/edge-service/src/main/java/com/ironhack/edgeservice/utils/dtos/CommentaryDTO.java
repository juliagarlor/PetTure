package com.ironhack.edgeservice.utils.dtos;

public class CommentaryDTO {

    private Long commentaryId;
    private String userName;
    private String commentBody;
    private Long postId;

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
