package com.irenejoy15.springboot.todos.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

// 1st implement UserDetails interface to integrate with Spring Security
// 3rd add @Entity and @Table annotations to map this class to a database table
@Table(name = "users")
@Entity
public class User implements UserDetails {

    // 4th add fields for user information (e.g., id, firstName, lastName, email, password, createdAt, updatedAt)
    // 6th add @Id and @GeneratedValue annotations to the id field, and @Column annotations to other fields as needed
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, length = 100 , unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    // STEP A.1 - Add a field to represent the user's authorities/roles (e.g., List<Authority> authorities) and annotate it with @ElementCollection to indicate that it's a collection of embeddable objects
    @ElementCollection(fetch = FetchType.EAGER) // Fetch authorities eagerly when loading a User
    @CollectionTable(name = "user_authorities", joinColumns = @JoinColumn(name = "user_id")) // Specify the name of the join table for user authorities
    private List<Authority> authorities; // One User have many Authorities/Roles
    //  END STEP A.1
    // private List<Todo> todos; // One User have many Todos
    // Default constructor (required by JPA)

    public User() {}

    // 2nd Override all methods from UserDetails interface (you can leave them unimplemented for now)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        // STEP A.2 - Implement getAuthorities() method to return the user's authorities/roles
        return authorities; // Return the user's authorities/roles
    }
    // END STEP A.2
    // STEP A.3 Generate Setters for authorities field
    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
    // END STEP A.3
    
    @Override
    public @Nullable String getPassword() {
        // TODO Auto-generated method stub
        // 5th implement getPassword() method to return the user's password
        return password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
         // 5th implement getUsername() method to return the user's email
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return UserDetails.super.isEnabled();
    }
    // END 2nd

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    // 6th generate getters and setters for all fields

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
