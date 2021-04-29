package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.utils.dtos.*;
import com.ironhack.edgeservice.utils.payload.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;

import java.util.*;

public interface IEdgeController {
    PictureDTO newPic(MultipartFile file);
    PictureDTO getPicById(Long id);

    PostDTO getPostAndPic(Long postId);
    List<PostDTO> getPublicPosts();
    PostDTO updateLicks(Long postId);
    List<PostDTO> getPostsByUser(String username);
    List<CommentaryDTO> getCommentariesInPost(Long postId);
    PostDTO newPost(PostDTO postDTO);
    CommentaryDTO addCommentary(CommentaryDTO commentaryDTO);

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    UserDTO getUserByUserName(String userName);
    ProfileDTO getProfileByUserName(String username);
    ResponseEntity<?> registerUser(UserDTO userDTO);
    ProfileDTO updateProfilePic(String userName, Long profilePic);
    List<ProfileDTO> getRequests(String userName);
    List<String> getRequested(String userName);
    List<ProfileDTO> getBuddies(String userName);
    UserDTO addABuddy(String userName, String buddy);
    UserDTO addRequest(String userName, String request);
    UserDTO removeRequest(String userName, String request);
    List<ProfileDTO> getPublicProfiles();
    void removeUser(String username);
}
