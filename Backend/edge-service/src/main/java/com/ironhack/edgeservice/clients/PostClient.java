package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.utils.dtos.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient("post-service")
public interface PostClient {

//    Returns post by id
    @GetMapping("/post/{id}")
    PostDTO getPostsById(@PathVariable Long id);

//    Returns posts by pictureId
    @GetMapping("/posts/picture/{pictureId}")
    List<PostDTO> getPostsByPicId(@PathVariable Long pictureId);

//    Returns post by username
    @GetMapping("/posts/user/{userName}")
    List<PostDTO> getPostsByUsername(@PathVariable String userName);

//    Returns all posts
    @GetMapping("/posts")
    List<PostDTO> getAllPosts();

//    Post a new post
    @PostMapping("/post")
    PostDTO newPost(@RequestBody PostDTO postDTO);

    @PutMapping("/licks/{postId}")
    PostDTO updateLicks(@PathVariable Long postId);

//    Remove a post
    @DeleteMapping("/post/{postId}")
    Long removePost(@PathVariable Long postId);

//    Remove posts by pictureID
    @DeleteMapping("/posts/picture/{pictureId}")
    void removePostsByPic(@PathVariable Long pictureId);

//    Get all the commentaries in a post
    @GetMapping("/commentaries/{postId}")
    List<CommentaryDTO> getCommentariesInPost(@PathVariable Long postId);

//    Add new commentary
    @PostMapping("/commentary")
    CommentaryDTO addCommentary(@RequestBody CommentaryDTO commentaryDTO);
}
