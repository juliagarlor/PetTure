package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.interfaces.*;
import com.ironhack.edgeservice.service.interfaces.*;
import com.ironhack.edgeservice.utils.dtos.*;
import com.ironhack.edgeservice.utils.payload.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
public class EdgeController implements IEdgeController {

    @Autowired
    private IEdgeService edgeService;

//    Picture part:
//    Return all pictures from a user. Permit all
    @GetMapping("/pics/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<PictureDTO> getPicsByUser(@PathVariable String userName){
        return edgeService.getPicsByUser(userName);
    }

//    Post a new picture. authenticated
    @PostMapping("/pic")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newPic(@RequestBody @Valid PictureDTO pictureDTO){
        return edgeService.newPic(pictureDTO);
    }

//    Update licks. authenticated
    @PutMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newLick(@PathVariable Long id){
        return edgeService.newLick(id);
    }

//    Delete picture. authenticated
    @DeleteMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePic(@PathVariable Long id){
        edgeService.removePic(id);
    }

//    Posts part:
//    Return a post with its picture. permit all
    @GetMapping("/post/view/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO getPostAndPic(@PathVariable Long postId){
        return edgeService.getPostAndPic(postId);
    }

//    Return public posts
    @GetMapping("/post/view/public")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getPublicPosts(){
        return edgeService.getPublicPosts();
    }

//    Return every post from an user
    @GetMapping("/post/view/by-user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> getPostsByUser(@PathVariable String username){
        return edgeService.getPostsByUser(username);
    }

//    Return commentaries from a post. permit all
    @GetMapping("/commentaries/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentaryDTO> getCommentariesInPost(@PathVariable Long postId){
        return edgeService.getCommentariesInPost(postId);
    }

//    Add new post and picture. authenticated
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO newPost(@RequestBody PostDTO postDTO){
        return edgeService.newPost(postDTO);
    }

//    Add a commentary to a post. authenticated
    @PostMapping("/commentary")
    @ResponseStatus(HttpStatus.OK)
    public CommentaryDTO addCommentary(@RequestBody CommentaryDTO commentaryDTO) {
        return edgeService.addCommentary(commentaryDTO);
    }

//    Remove a post and its pic. authenticated
    @DeleteMapping("/post/remove/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable Long postId){
        edgeService.removePost(postId);
    }

//    User part:
//    login. permit all
    @PostMapping("/user/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        return edgeService.authenticateUser(loginRequest);
    }

//    Get user by username, with all its pictures. permit all
    @GetMapping("/user/search/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserByUserName(@PathVariable String userName){
        return edgeService.getUserByUserName(userName);
    }

//    Register. permit all
    @PostMapping("/user/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        return edgeService.registerUser(userDTO);
    }

//    Get buddies. permit all
    @GetMapping("/user/buddies/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileDTO> getBuddies(@PathVariable String userName){
        return edgeService.getBuddies(userName);
    }

//    Get requests. authenticated
    @GetMapping("/user/requests/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileDTO> getRequests(@PathVariable String userName){
        return edgeService.getRequests(userName);
    }

//    Update profile pic. authenticated
    @PutMapping("/user/profile-pic/{userName}")
    public ProfileDTO updateProfilePic(@PathVariable String userName, @RequestBody String profilePic){
        return edgeService.updateProfilePic(userName, profilePic);
    }

//    Add new buddy. authenticated
    @PutMapping("/user/buddy/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addABuddy(@PathVariable String userName, @RequestBody String buddy){
        return edgeService.addABuddy(userName, buddy);
    }

//    Add new request. authenticated
    @PutMapping("/user/request/{userName}")
    public UserDTO addRequest(@PathVariable String userName, @RequestBody String request){
        return edgeService.addRequest(userName, request);
    }

//    Remove requests. authenticated
    @PutMapping("/user/remove/request/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO removeRequest(@PathVariable String userName, @RequestBody String request){
        return edgeService.removeRequest(userName, request);
    }

//    Remove user, its pictures and posts. authenticated
    @DeleteMapping("/user/{userName}")
    public void removeUser(@PathVariable String userName){
        edgeService.removeUser(userName);
    }
}
