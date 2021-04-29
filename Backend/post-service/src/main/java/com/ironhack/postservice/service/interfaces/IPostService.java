package com.ironhack.postservice.service.interfaces;

import com.ironhack.postservice.utils.dtos.*;

import java.util.*;

public interface IPostService {
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
