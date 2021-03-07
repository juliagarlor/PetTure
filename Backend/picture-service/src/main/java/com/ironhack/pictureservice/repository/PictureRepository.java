package com.ironhack.pictureservice.repository;

import com.ironhack.pictureservice.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findByUserName(String userName);
}
