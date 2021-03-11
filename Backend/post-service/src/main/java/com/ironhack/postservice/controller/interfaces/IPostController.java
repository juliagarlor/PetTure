package com.ironhack.postservice.controller.interfaces;

import com.ironhack.postservice.utils.dtos.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface IPostController {
    PostDTO getPostsById(Long id);
    List<PostDTO> getPostsByPicId(Long pictureId);
    List<PostDTO> getAllPosts();
    PostDTO newPost(PostDTO postDTO);
    Long removePost(Long postId);
    void removePostsByPic(Long pictureId);
}
