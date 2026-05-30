package com.irenejoy15.springboot.todos.util;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.irenejoy15.springboot.todos.entity.User;

@Component
public class FindAuthenticatedUserImpl implements FindAuthenticatedUser {

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication(); 
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException("User is not authenticated | Access Denied");
        }
        return (User) authentication.getPrincipal();
    }

}
