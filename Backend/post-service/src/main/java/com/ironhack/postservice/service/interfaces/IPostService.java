package com.ironhack.postservice.service.interfaces;

import com.ironhack.postservice.utils.dtos.*;

import java.util.*;

public interface IPostService {
    PostDTO getPostsById(Long id);
    List<PostDTO> getPostsByPicId(Long pictureId);
    List<PostDTO> getAllPosts();
    PostDTO newPost(PostDTO postDTO);
    Long removePost(Long postId);
    void removePostsByPic(Long pictureId);
}
