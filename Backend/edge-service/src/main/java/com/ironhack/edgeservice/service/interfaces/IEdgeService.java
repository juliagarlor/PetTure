package com.ironhack.edgeservice.service.interfaces;

import com.ironhack.edgeservice.utils.dtos.*;
import com.ironhack.edgeservice.utils.payload.*;
import org.springframework.http.*;

import java.util.*;

public interface IEdgeService {
    List<PictureDTO> getPicsByUser(String userName);
    PictureDTO newPic(PictureDTO pictureDTO);
    PictureDTO newLick(Long id);
    void removePic(Long id);

    PostDTO getPostAndPic(Long postId);
    List<CommentaryDTO> getCommentariesInPost(Long postId);
    PostDTO newPost(PostDTO postDTO);
    CommentaryDTO addCommentary(CommentaryDTO commentaryDTO);
    void removePost(Long postId);

//    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    UserDTO getUserByUserName(String userName);
    List<ProfileDTO> getBuddies(String userName);
    List<ProfileDTO> getRequests(String userName);
    UserDTO addABuddy(String userName, String buddy);
    UserDTO removeRequest(String userName, String request);
}
