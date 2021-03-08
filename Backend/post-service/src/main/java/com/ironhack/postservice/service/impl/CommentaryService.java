package com.ironhack.postservice.service.impl;

import com.ironhack.postservice.model.*;
import com.ironhack.postservice.repository.*;
import com.ironhack.postservice.service.interfaces.*;
import com.ironhack.postservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CommentaryService implements ICommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;
    @Autowired
    private PostService postService;

    public CommentaryDTO addCommentary(CommentaryDTO commentaryDTO) {
        Commentary newCommentary = new Commentary();

        Post associated = postService.checkPostId(commentaryDTO.getPostId());
        newCommentary.setBody(commentaryDTO.getCommentBody());
        newCommentary.setUserName(commentaryDTO.getUserName());
        newCommentary.setPost(associated);

        commentaryRepository.save(newCommentary);
        return new CommentaryDTO(newCommentary);
    }

    public List<CommentaryDTO> getCommentariesInPost(Long postId) {
        List<Commentary> commentaries = commentaryRepository.findByPostId(postId);
        List<CommentaryDTO> output = new ArrayList<>();

        for (Commentary commentary : commentaries){
            output.add(new CommentaryDTO(commentary));
        }

        return output;
    }
}
