package com.ironhack.postservice.service.interfaces;

import com.ironhack.postservice.utils.dtos.*;

import java.util.*;

public interface ICommentaryService {
    CommentaryDTO addCommentary(CommentaryDTO commentaryDTO);
    List<CommentaryDTO> getCommentariesInPost(Long postId);
}
