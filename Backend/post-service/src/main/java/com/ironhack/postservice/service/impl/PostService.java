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

    public List<PostDTO> getPostsByPicId(Long pictureId) {
        List<Post> postList = postRepository.findByPictureId(pictureId);
        List<PostDTO> output = new ArrayList<>();
        for(Post post : postList){
            output.add(new PostDTO(post));
        }
        return output;
    }

    public List<PostDTO> getPostsByUsername(String userName) {
        List<Post> postList = postRepository.findByUserName(userName);
        List<PostDTO> output = new ArrayList<>();
        for (Post post : postList){
            output.add(new PostDTO(post));
        }
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
        System.out.println(postDTO.getUserName());
        Post newPost = new Post(postDTO);
        System.out.println(newPost.getUserName());
        postRepository.save(newPost);

        return new PostDTO(newPost);
    }

    public PostDTO updateLicks(Long postId) {
        Post post = checkPostId(postId);
        int currentLicks = post.getLicks() + 1;
        post.setLicks(currentLicks);
        PostDTO output = new PostDTO(postRepository.save(post));
        output.setLicks(currentLicks);
        return output;
    }

    public Long removePost(Long postId) {
        Post postToRemove = checkPostId(postId);

        Long pictureId = postToRemove.getPictureId();

        List<Commentary> commentaries = commentaryRepository.findByPostId(postToRemove.getId());
        commentaryRepository.deleteAll(commentaries);
        postRepository.delete(postToRemove);

        return pictureId;
    }

    public void removePostsByPic(Long pictureId) {
        List<Post> postsToRemove = postRepository.findByPictureId(pictureId);

        for (Post post : postsToRemove){
            removePost(post.getId());
        }
    }

    public Post checkPostId(Long id){
        Optional<Post> output = postRepository.findById(id);

        if (output.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not post with such an ID");
        }

        return output.get();
    }
}
