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
//    Return all pictures from a user
    @GetMapping("/pics/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<PictureDTO> getPicsByUser(@PathVariable String userName){
        return edgeService.getPicsByUser(userName);
    }

//    Post a new picture
    @PostMapping("/pic")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newPic(@RequestBody @Valid PictureDTO pictureDTO){
        return edgeService.newPic(pictureDTO);
    }

//    Update licks
    @PutMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PictureDTO newLick(@PathVariable Long id){
        return edgeService.newLick(id);
    }

//    Delete picture
    @DeleteMapping("/pic/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePic(@PathVariable Long id){
        edgeService.removePic(id);
    }

//    Posts part:
//    Return a post with its picture
    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO getPostAndPic(@PathVariable Long postId){
        return edgeService.getPostAndPic(postId);
    }

//    Return commentaries from a post
    @GetMapping("/commentaries/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentaryDTO> getCommentariesInPost(@PathVariable Long postId){
        return edgeService.getCommentariesInPost(postId);
    }

//    Add new post and picture
    @PostMapping("/post")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO newPost(@RequestBody PostDTO postDTO){
        return edgeService.newPost(postDTO);
    }

//    Add a commentary to a post
    @PostMapping("/commentary")
    @ResponseStatus(HttpStatus.OK)
    public CommentaryDTO addCommentary(@RequestBody @Valid CommentaryDTO commentaryDTO) {
        return edgeService.addCommentary(commentaryDTO);
    }

//    Remove a post and its pic
    @DeleteMapping("/post/remove/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePost(@PathVariable Long postId){
        edgeService.removePost(postId);
    }

//    User part:
//    login
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        return edgeService.authenticateUser(loginRequest);
    }

//    Get buddies
    @GetMapping("/user/buddies/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileDTO> getBuddies(@PathVariable String userName){
        return edgeService.getBuddies(userName);
    }

//    Get requests
    @GetMapping("/user/requests/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProfileDTO> getRequests(@PathVariable String userName){
        return edgeService.getRequests(userName);
    }

//    Add new buddy
    @PutMapping("/user/buddy/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO addABuddy(@PathVariable String userName, @RequestBody String buddy){
        return edgeService.addABuddy(userName, buddy);
    }
}
