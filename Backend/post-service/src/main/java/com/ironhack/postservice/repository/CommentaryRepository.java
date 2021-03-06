package com.ironhack.postservice.repository;

import com.ironhack.postservice.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
