package com.devhub.devhub.service;

import com.devhub.devhub.model.AppUser;
import com.devhub.devhub.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    private final AppUserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        logger.info("Загружен пользователь: {}", user.getUsername());
        logger.info("Пароль пользователя (из базы): {}", user.getPassword());
        logger.info("Роль пользователя: {}", user.getRole());

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        List<GrantedAuthority> authorities = Collections.singletonList(authority);

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}