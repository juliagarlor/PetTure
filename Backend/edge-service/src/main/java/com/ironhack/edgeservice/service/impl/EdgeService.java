package com.ironhack.edgeservice.service.impl;

import com.ironhack.edgeservice.clients.*;
import com.ironhack.edgeservice.security.jwt.*;
import com.ironhack.edgeservice.security.services.*;
import com.ironhack.edgeservice.service.interfaces.*;
import com.ironhack.edgeservice.utils.classes.*;
import com.ironhack.edgeservice.utils.dtos.*;
import com.ironhack.edgeservice.utils.payload.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cloud.circuitbreaker.resilience4j.*;
import org.springframework.cloud.client.circuitbreaker.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

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
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtils.generateJwtToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    public UserDTO getUserByUserName(String userName) {
        UserDTO output = userClient.getUserByUserName(userName);
        output.setPics(pictureClient.getPicsByUser(userName));
        output.setBuddiesAmount(output.getBuddies().size());
        return output;
    }

    public ResponseEntity<?> registerUser(UserDTO userDTO) {
        return userClient.registerUser(userDTO);
    }

    public ProfileDTO updateProfilePic(String userName, String profilePic) {
        return userClient.updateProfilePic(userName, profilePic);
    }

    public List<ProfileDTO> getBuddies(String userName) {
        return userClient.getBuddies(userName);
    }

    public List<ProfileDTO> getRequests(String userName) {
        return userClient.getRequests(userName);
    }

    public UserDTO addABuddy(String userName, String buddy) {
//        Removing request of the new buddy
        userClient.removeRequest(userName, buddy);
//        And turning it into a buddy
        return userClient.addABuddy(userName, buddy);
    }

    public UserDTO addRequest(String userName, String request) {
        return userClient.addRequest(userName, request);
    }

    public UserDTO removeRequest(String userName, String request) {
        return userClient.removeRequest(userName, request);
    }

    public void removeUser(String userName) {
//        Getting pictures and its ids
        List<PictureDTO> picsToRemove = getPicsByUser(userName);

        for (PictureDTO pic : picsToRemove){
//            Removing posts and commentaries from a picture
            postClient.removePostsByPic(pic.getPicId());
//            Removing the picture
            removePic(pic.getPicId());
        }
//        Removing the user
        userClient.removeUser(userName);
    }
}
