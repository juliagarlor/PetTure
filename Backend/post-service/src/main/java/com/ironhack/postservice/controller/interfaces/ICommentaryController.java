package com.ironhack.postservice.controller.interfaces;

import com.ironhack.postservice.utils.dtos.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface ICommentaryController {
    CommentaryDTO addCommentary(CommentaryDTO commentaryDTO);
    List<CommentaryDTO> getCommentariesInPost(Long postId);
}
