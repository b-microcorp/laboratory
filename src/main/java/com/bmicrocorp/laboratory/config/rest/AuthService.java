package com.bmicrocorp.laboratory.config.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bmicrocorp.laboratory.model.entities.User;
import com.bmicrocorp.laboratory.repository.UserRepository;

@Component("userDetailService")
public class AuthService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        final User us = this.repository.findByEmail(username);
        if(us == null) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }
        
        return org.springframework.security.core.userdetails.User.withUsername(us.getUsername())
                .password(us.getPassword())
                .authorities(us.getAuthorities())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
