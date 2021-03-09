package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.utils.dtos.*;
import com.ironhack.edgeservice.utils.payload.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface IEdgeController {
    List<PictureDTO> getPicsByUser(String userName);
    PictureDTO newPic(PictureDTO pictureDTO);
    PictureDTO newLick(Long id);
    void removePic(Long id);

    PostDTO getPostAndPic(Long postId);
    List<CommentaryDTO> getCommentariesInPost(Long postId);
    PostDTO newPost(PostDTO postDTO);
    CommentaryDTO addCommentary(CommentaryDTO commentaryDTO);
    void removePost(Long postId);

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
    List<ProfileDTO> getBuddies(String userName);
    List<ProfileDTO> getRequests(String userName);
    UserDTO addABuddy(String userName, String buddy);
}
