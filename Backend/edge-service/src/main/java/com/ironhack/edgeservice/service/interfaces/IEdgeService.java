package com.ironhack.edgeservice.service.interfaces;

import com.ironhack.edgeservice.utils.dtos.*;
import com.ironhack.edgeservice.utils.payload.*;
import org.springframework.http.*;
import org.springframework.web.multipart.*;

import java.util.*;

public interface IEdgeService {
    PictureDTO newPic(MultipartFile file);
    void removePic(Long id);

    PostDTO getPostAndPic(Long postId);
    List<PostDTO> getPublicPosts();
    PostDTO updateLicks(Long postId);
    List<PostDTO> getPostsByUser(String username);
    List<CommentaryDTO> getCommentariesInPost(Long postId);
    PostDTO newPost(PostDTO postDTO);
    CommentaryDTO addCommentary(CommentaryDTO commentaryDTO);
    void removePost(Long postId);

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    UserDTO getUserByUserName(String userName);
    ProfileDTO getProfileByUserName(String username);
    ResponseEntity<?> registerUser(UserDTO userDTO);
    ProfileDTO updateProfilePic(String userName, Long profilePic);
    List<ProfileDTO> getBuddies(String userName);
    List<ProfileDTO> getRequests(String userName);
    UserDTO addABuddy(String userName, String buddy);
    UserDTO addRequest(String userName, String request);
    UserDTO removeRequest(String userName, String request);
    void removeUser(String userName);
    List<ProfileDTO> getPublicProfiles();
}
