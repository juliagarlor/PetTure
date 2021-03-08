package com.ironhack.postservice.service.impl;

import com.ironhack.postservice.model.*;
import com.ironhack.postservice.repository.*;
import com.ironhack.postservice.service.interfaces.*;
import com.ironhack.postservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.util.*;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentaryRepository commentaryRepository;

    public PostDTO getPostsById(Long id) {
        Post post = checkPostId(id);
        PostDTO output = new PostDTO(post);
        return output;
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> output = new ArrayList<>();

        for (Post post : posts){
            output.add(new PostDTO(post));
        }

        return output;
    }

    public PostDTO newPost(PostDTO postDTO) {
        Post newPost = new Post(postDTO);
        postRepository.save(newPost);

        return new PostDTO(newPost);
    }

    public Long removePost(Long postId) {
        Post postToRemove = checkPostId(postId);

        Long pictureId = postToRemove.getPictureId();

        List<Commentary> commentaries = commentaryRepository.findByPostId(postToRemove.getId());
        commentaryRepository.deleteAll(commentaries);
        postRepository.delete(postToRemove);

        return pictureId;
    }

    public Post checkPostId(Long id){
        Optional<Post> output = postRepository.findById(id);

        if (output.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not post with such an ID");
        }

        return output.get();
    }
}
