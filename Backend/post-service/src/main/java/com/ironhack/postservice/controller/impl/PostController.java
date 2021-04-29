package com.ironhack.postservice.controller.impl;

import com.ironhack.postservice.controller.interfaces.*;
import com.ironhack.postservice.service.interfaces.*;
import com.ironhack.postservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class PostController implements IPostController {

    @Autowired
    private IPostService postService;

//    Returns post by id
    @GetMapping("/post/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO getPostsById(@PathVariable Long id){
        return postService.getPostsById(id);
    }

//    Returns posts by pictureId
    @GetMapping("/posts/picture/{pictureId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getPostsByPicId(@PathVariable Long pictureId){
    return postService.getPostsByPicId(pictureId);
    }

//    Returns posts by username
    @GetMapping("/posts/user/{userName}")
    public List<PostDTO> getPostsByUsername(@PathVariable String userName){
        return postService.getPostsByUsername(userName);
    }

//    Returns all posts
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

//    Post a new post
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO newPost(@RequestBody @Valid PostDTO postDTO){
        return postService.newPost(postDTO);
    }

//    Update licks
    @PutMapping("/licks/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO updateLicks(@PathVariable Long postId){
        return postService.updateLicks(postId);
    }

//    Remove a post and return pictureId
    @DeleteMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public Long removePost(@PathVariable Long postId){
        return postService.removePost(postId);
    }

//    Remove posts by pictureID
    @DeleteMapping("/posts/picture/{pictureId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePostsByPic(@PathVariable Long pictureId){
        postService.removePostsByPic(pictureId);
    }

//    Remove posts by username
    @DeleteMapping("/posts/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> removePostsByUsername(@PathVariable String username){
        return postService.removePostsByUsername(username);
    }
}
