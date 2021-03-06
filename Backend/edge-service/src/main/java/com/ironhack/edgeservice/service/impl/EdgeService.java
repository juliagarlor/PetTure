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
import org.springframework.web.multipart.MultipartFile;


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
    public PictureDTO newPic(MultipartFile file) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        return pictureCircuitBreaker.run(() -> pictureClient.newPic(file), throwable -> fallBack.picFallBack());
    }

    public PictureDTO getPicById(Long id) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        return pictureCircuitBreaker.run(() -> pictureClient.getPicById(id), throwable -> fallBack.picFallBack());
    }

    //    Posts part
    public PostDTO getPostAndPic(Long postId) {
        CircuitBreaker pictureCircuitBreaker = circuitBreakerFactory.create("picture-service");
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");

        PostDTO post = postCircuitBreaker.run(() -> postClient.getPostsById(postId), throwable -> fallBack.postFallBack());
        PictureDTO pic = pictureCircuitBreaker.run(() -> pictureClient.getPicById(post.getPictureId()),
                throwable -> fallBack.picFallBack());
        return post;
    }

    public List<PostDTO> getPublicPosts() {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
//        Get all public profiles' usernames
        List<String> publicUsernames = userCircuitBreaker.run(() -> userClient.getPublicProfiles(),
                throwable -> fallBack.userNameFallBack());
        List<PostDTO> output = new ArrayList<>();

        for (String username : publicUsernames){
            output.addAll(getPostsByUser(username));
        }
        return output;
    }

    public PostDTO updateLicks(Long postId) {
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");
        return postCircuitBreaker.run(() -> postClient.updateLicks(postId),
                throwable -> fallBack.postFallBack());
    }

    public List<PostDTO> getPostsByUser(String username) {
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");
        return postCircuitBreaker.run(() -> postClient.getPostsByUsername(username),
                throwable -> fallBack.postListFallBack());
    }

    public List<CommentaryDTO> getCommentariesInPost(Long postId) {
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");
        return postCircuitBreaker.run(() -> postClient.getCommentariesInPost(postId),
                throwable -> fallBack.commentListFallBack());
    }

    public PostDTO newPost(PostDTO postDTO) {
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");

        PostDTO newPost = postCircuitBreaker.run(() -> postClient.newPost(postDTO), throwable -> fallBack.postFallBack());
        postDTO.setPostId(newPost.getPostId());
        return postDTO;
    }

    public void removePost(Long postId) {
        Long picId = postClient.removePost(postId);
        pictureClient.removePic(picId);
    }

    public CommentaryDTO addCommentary(CommentaryDTO commentaryDTO) {
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");

        return postCircuitBreaker.run(() -> postClient.addCommentary(commentaryDTO),
                throwable -> fallBack.commentFallBack());
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
//        We need circuit Breakers here, but you'll have to look where
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtils.generateJwtToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    public UserDTO getUserByUserName(String userName) {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
        CircuitBreaker postCircuitBreaker = circuitBreakerFactory.create("post-service");

        UserDTO output = userCircuitBreaker.run(() -> userClient.getUserByUserName(userName),
                throwable -> fallBack.userFallBack());
        List<PostDTO> posts = postCircuitBreaker.run(() ->postClient.getPostsByUsername(userName),
                throwable -> fallBack.postListFallBack());
        output.setPosts(posts);
        output.setBuddyNum(output.getBuddies().size());
        return output;
    }

    public ProfileDTO getProfileByUserName(String username) {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
        ProfileDTO output = userCircuitBreaker.run(() -> userClient.getProfileByUserName(username),
                throwable -> fallBack.profileFallBack());
        return output;
    }

    public ResponseEntity<?> registerUser(UserDTO userDTO) {
        return userClient.registerUser(userDTO);
    }

    public ProfileDTO updateProfilePic(String userName, Long profilePic) {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
        ProfileDTO output = userCircuitBreaker.run(() -> userClient.updateProfilePic(userName, profilePic),
                throwable -> fallBack.profileFallBack());
        return output;
    }

    public List<ProfileDTO> getRequests(String userName) {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
        List<ProfileDTO> output = userCircuitBreaker.run(() -> userClient.getRequests(userName),
                throwable -> fallBack.profileListFallBack());
        return output;
    }

    public List<String> getRequested(String userName) {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
        List<String> output = userCircuitBreaker.run(() -> userClient.getRequested(userName),
                throwable -> fallBack.userNameFallBack());
        return output;
    }

    public List<ProfileDTO> getBuddies(String userName) {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
        List<ProfileDTO> output = userCircuitBreaker.run(() -> userClient.getBuddies(userName),
                throwable -> fallBack.profileListFallBack());
        return output;
    }

    public UserDTO addABuddy(String userName, String buddy) {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
//        And turning it into a buddy
        UserDTO output = userCircuitBreaker.run(() -> userClient.addABuddy(userName, buddy),
                throwable -> fallBack.userFallBack());
        return output;
    }

    public UserDTO addRequest(String userName, String request) {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
        UserDTO output = userCircuitBreaker.run(() -> userClient.addRequest(userName, request),
                throwable -> fallBack.userFallBack());
        return output;
    }

    public UserDTO removeRequest(String userName, String request) {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
        UserDTO output = userCircuitBreaker.run(() -> userClient.removeRequest(userName, request),
                throwable -> fallBack.userFallBack());
        return output;
    }

    public List<ProfileDTO> getPublicProfiles() {
        CircuitBreaker userCircuitBreaker = circuitBreakerFactory.create("user-service");
        List<String> publicUsernames = userCircuitBreaker.run(() -> userClient.getPublicProfiles(),
                throwable -> fallBack.userNameFallBack());
        if (publicUsernames.size() > 5){
            publicUsernames.removeIf(username -> (publicUsernames.indexOf(username) > 4));
        }

        List<ProfileDTO> output = new ArrayList<>();
        for (String name : publicUsernames){
            output.add(getProfileByUserName(name));
        }
        return output;
    }

    public void removeUser(String username) {
//        removing user
        Long profilePic = userClient.removeUser(username);
//        removing posts
        List<Long> picturesId = postClient.removePostsByUsername(username);
//        removing pictures. Profile and posts
        picturesId.add(profilePic);
        pictureClient.removePicsByUser(picturesId);
    }
}
