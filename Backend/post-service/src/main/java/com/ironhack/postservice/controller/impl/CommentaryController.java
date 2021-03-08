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
public class CommentaryController implements ICommentaryController {

    @Autowired
    private ICommentaryService commentaryService;

//    Get all the commentaries in a post
    @GetMapping("/commentaries/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentaryDTO> getCommentariesInPost(@PathVariable Long postId){
        return commentaryService.getCommentariesInPost(postId);
    }

//    Add new commentary
    @PostMapping("/commentary")
    @ResponseStatus(HttpStatus.OK)
    public CommentaryDTO addCommentary(@RequestBody @Valid CommentaryDTO commentaryDTO) {
        return commentaryService.addCommentary(commentaryDTO);
    }
}
