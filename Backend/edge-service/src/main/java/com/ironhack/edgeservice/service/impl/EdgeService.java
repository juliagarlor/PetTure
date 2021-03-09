package com.ironhack.edgeservice.service.impl;

import com.ironhack.edgeservice.clients.*;
import com.ironhack.edgeservice.service.interfaces.*;
import com.ironhack.edgeservice.utils.classes.*;
import com.ironhack.edgeservice.utils.dtos.*;
import com.ironhack.edgeservice.utils.payload.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cloud.circuitbreaker.resilience4j.*;
import org.springframework.cloud.client.circuitbreaker.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class EdgeService implements IEdgeService {

    @Autowired
    private PictureClient pictureClient;
    @Autowired
    private PostClient postClient;
    @Autowired
    private UserClient userClient;

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();
    private FallBack fallBack = new FallBack();

//    Picture part
    public List<PictureDTO> getPicsByUser(String userName) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        return pictureCircuitBreaker.run(() -> pictureClient.getPicsByUser(userName), throwable -> fallBack.listPicFallBack());
    }

    public PictureDTO newPic(PictureDTO pictureDTO) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        return pictureCircuitBreaker.run(() -> pictureClient.newPic(pictureDTO), throwable -> fallBack.picFallBack());
    }

    public PictureDTO newLick(Long id) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        return pictureCircuitBreaker.run(() -> pictureClient.newLick(id), throwable -> fallBack.picFallBack());
    }

    public void removePic(Long id) {
        pictureClient.removePic(id);
    }

//    Posts part
    public PostDTO getPostAndPic(Long postId) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");

        PostDTO post = postCircuitBreaker.run(() -> postClient.getPostsById(postId), throwable -> fallBack.postFallBack());
        PictureDTO pic = pictureCircuitBreaker.run(() -> pictureClient.getPicById(post.getPictureId()),
                throwable -> fallBack.picFallBack());
        post.setPicture(pic);
        post.setCommentaries(new ArrayList<>());
        return post;
    }

    public List<CommentaryDTO> getCommentariesInPost(Long postId) {
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");
        return postCircuitBreaker.run(() -> postClient.getCommentariesInPost(postId),
                throwable -> fallBack.commentListFallBack());
    }

    public PostDTO newPost(PostDTO postDTO) {
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");

        PictureDTO pic = newPic(postDTO.getPicture());
        postDTO.setPicture(pic);
        postDTO.setPictureId(pic.getPicId());
        PostDTO newPost = postCircuitBreaker.run(() -> postClient.newPost(postDTO), throwable -> fallBack.postFallBack());
        postDTO.setPostId(newPost.getPostId());
        postDTO.setCommentaries(new ArrayList<>());
        return postDTO;
    }

    public CommentaryDTO addCommentary(CommentaryDTO commentaryDTO) {
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");

        return postCircuitBreaker.run(() -> postClient.addCommentary(commentaryDTO),
                throwable -> fallBack.commentFallBack());
    }

    public void removePost(Long postId) {
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");

        Long picId = postCircuitBreaker.run(() -> postClient.removePost(postId),
                throwable -> fallBack.picIdFallBack());
        pictureClient.removePic(picId);
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        return userClient.authenticateUser(loginRequest);
    }

    public List<ProfileDTO> getBuddies(String userName) {
        return userClient.getBuddies(userName);
    }

    public List<ProfileDTO> getRequests(String userName) {
        return userClient.getRequests(userName);
    }

    public UserDTO addABuddy(String userName, String buddy) {
        return userClient.addABuddy(userName, buddy);
    }
}
