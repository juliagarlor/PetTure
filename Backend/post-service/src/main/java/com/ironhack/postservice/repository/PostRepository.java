package com.ironhack.postservice.repository;

import com.ironhack.postservice.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByPictureId(Long pictureId);
    List<Post> findByUserName(String userName);
}
