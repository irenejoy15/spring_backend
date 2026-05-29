package com.irenejoy15.springboot.todos.response;
import java.util.List;

import com.irenejoy15.springboot.todos.entity.Authority;

public class UserResponse {
    private Long id;   
    private String fullName;
     private String email;
    private List<Authority> authorities;

    public UserResponse(Long id, String fullName, String email, List<Authority> authorities) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    
    
}
