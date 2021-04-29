package com.ironhack.postservice.controller.interfaces;

import com.ironhack.postservice.utils.dtos.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface IPostController {
    PostDTO getPostsById(Long id);
    List<PostDTO> getPostsByPicId(Long pictureId);
    List<PostDTO> getPostsByUsername(String userName);
    List<PostDTO> getAllPosts();
    PostDTO newPost(PostDTO postDTO);
    PostDTO updateLicks(Long postId);
    Long removePost(Long postId);
    void removePostsByPic(Long pictureId);
    List<Long> removePostsByUsername(String username);
}
