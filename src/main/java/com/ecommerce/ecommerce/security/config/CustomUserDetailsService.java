package com.ecommerce.ecommerce.security.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return User.builder()
                .username("admin")
                .password("{noop}password")
                .authorities(Collections.emptyList())
                .build();
    }
}