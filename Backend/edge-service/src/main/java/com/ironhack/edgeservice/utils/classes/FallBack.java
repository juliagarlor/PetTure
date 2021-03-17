package com.ironhack.edgeservice.utils.classes;

import com.ironhack.edgeservice.utils.dtos.*;
import org.springframework.security.core.parameters.*;

import java.util.*;

public class FallBack {

    public List<PictureDTO> listPicFallBack(){
        return new ArrayList<>();
    }

    public PictureDTO picFallBack(){
        PictureDTO output = new PictureDTO();
        output.setPictureName("notFound.jpg");
        output.setType("jpg");
        return output;
    }

    public PostDTO postFallBack(){
        PostDTO output = new PostDTO();
        output.setPostBody("Post service counting sheeps...");
        output.setPictureId(0L);
        output.setLicks(0);
        output.setUserName("MissingNo");
        return output;
    }

    public List<PostDTO> postListFallBack(){
        return new ArrayList<>();
    }

    public List<CommentaryDTO> commentListFallBack(){
        return new ArrayList<>();
    }

    public CommentaryDTO commentFallBack(){
        CommentaryDTO output = new CommentaryDTO();
        output.setCommentBody("What do you think about macroeconomy?");
        output.setPostId(0L);
        output.setUserName("");
        return output;
    }

    public Long picIdFallBack(){
        return 0L;
    }

    public UserDTO userFallBack(){
        UserDTO output = new UserDTO();
        output.setUserName("Invisible man");
        output.setPassword("password");
        output.setProfilePicture(1L);
        output.setPosts(new ArrayList<>());
        output.setBuddyNum(0);
        output.setBuddies(new ArrayList<>());
        output.setRequests(new ArrayList<>());
        output.setRole("USER");
        output.setVisibility("PUBLIC");
        return output;
    }

    public ProfileDTO profileFallBack(){
        ProfileDTO output = new ProfileDTO();
        output.setUserName("Invisible man");
        output.setProfilePic(1L);
        return output;
    }

    public List<ProfileDTO> profileListFallBack(){
        return new ArrayList<>();
    }

    public List<String> userNameFallBack(){
        return new ArrayList<>();
    }
}
