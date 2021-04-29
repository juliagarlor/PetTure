package com.ironhack.userservice.repository;

import com.ironhack.userservice.model.*;
import com.ironhack.userservice.utils.enums.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.util.*;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT userName FROM User WHERE visibility = :visibility")
    List<String> findPublicUserNames(@Param("visibility") Visibility visibility);

    @Query(value = "SELECT DISTINCT requested_user FROM request_to_user WHERE requesting_user = :username", nativeQuery = true)
    List<String> findRequestedUsers(@Param("username") String username);

    @Modifying
    @Query(value = "DELETE FROM request_to_user WHERE requesting_user = :username OR requested_user = :username", nativeQuery = true)
    void deleteFromRequests(@Param("username") String username);

    @Modifying
    @Query(value = "DELETE FROM user_buddies WHERE `current_user` = :username OR buddy = :username", nativeQuery = true)
    void deleteFromBuddies(@Param("username") String username);
}
