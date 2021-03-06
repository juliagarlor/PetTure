package com.ironhack.userservice.repository;

import com.ironhack.userservice.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
