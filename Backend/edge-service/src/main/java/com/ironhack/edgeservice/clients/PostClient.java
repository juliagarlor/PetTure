package com.ironhack.edgeservice.clients;

import com.ironhack.edgeservice.utils.dtos.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@FeignClient("post-service")
public interface PostClient {

//    Returns post by id
    @GetMapping("/post/{id}")
    PostDTO getPostsById(@PathVariable Long id);

//    Returns all posts
    @GetMapping("/posts")
    List<PostDTO> getAllPosts();

//    Post a new post
    @PostMapping("/post")
    PostDTO newPost(@RequestBody PostDTO postDTO);

//    Remove a post
    @DeleteMapping("/post/{postId}")
    Long removePost(@PathVariable Long postId);

//    Get all the commentaries in a post
    @GetMapping("/commentaries/{postId}")
    List<CommentaryDTO> getCommentariesInPost(@PathVariable Long postId);

//    Add new commentary
    @PostMapping("/commentary")
    CommentaryDTO addCommentary(@RequestBody CommentaryDTO commentaryDTO);
}
