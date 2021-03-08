package com.ironhack.userservice.repository;

import com.ironhack.userservice.model.*;
import com.ironhack.userservice.utils.enums.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT userName FROM User WHERE visibility = :visibility")
    List<String> findPublicUserNames(@Param("visibility") Visibility visibility);
}
