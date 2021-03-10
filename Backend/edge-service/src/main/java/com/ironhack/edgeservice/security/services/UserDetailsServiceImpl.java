package com.ironhack.edgeservice.security.services;

import com.ironhack.edgeservice.clients.*;
import com.ironhack.edgeservice.utils.dtos.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        UserDTO userDTO = userClient.getUserByUserName(userName);
        userDTO.setRole(new RoleDTO());
        return new UserDetailsImpl(userDTO);
    }
}
