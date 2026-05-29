package com.irenejoy15.springboot.todos.entity;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Embeddable;

// 3rd add @Embeddable annotation to indicate that this class can be embedded in other entities (e.g., User) to represent user authorities/roles
@Embeddable
// 1st Added Implementation of GrantedAuthority interface to represent user roles/authorities in Spring Security
public class Authority implements GrantedAuthority{

    // 4th add a field to represent the authority/role (e.g., authority)
    private String authority;

    // 5th add a default constructor and a parameterized constructor for creating Authority objects
    public Authority() {}

    // 6th add a parameterized constructor for creating Authority objects ctrl + . to generate constructor
    public Authority(String authority) {
        this.authority = authority;
    }

    // 2nd Implement methods from GrantedAuthority interface (e.g., getAuthority())
    @Override
    public @Nullable String getAuthority() {
        // TODO Auto-generated method stub
        return authority;
    }
   
    
}
