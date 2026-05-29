package com.irenejoy15.springboot.todos.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.irenejoy15.springboot.todos.entity.Authority;
import com.irenejoy15.springboot.todos.entity.User;
import com.irenejoy15.springboot.todos.repository.UserRepository;
import com.irenejoy15.springboot.todos.response.UserResponse;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserInfo() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication(); 
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException("User is not authenticated | Access Denied");
        }
        User user = (User) authentication.getPrincipal();
        return new UserResponse(
            user.getId(),
            user.getFirstName() +" "+ user.getLastName(),
            user.getEmail(),
            user.getAuthorities().stream().map(auth->(Authority) auth).toList()
        );
    }
}
