package com.ironhack.userservice.security.services;

import com.ironhack.userservice.model.User;
import com.ironhack.userservice.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import javax.transaction.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = userRepository.findById(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "+ userName));
        return UserDetailsImpl.build(user);
    }
}
