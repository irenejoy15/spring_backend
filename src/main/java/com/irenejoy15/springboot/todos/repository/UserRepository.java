package com.irenejoy15.springboot.todos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irenejoy15.springboot.todos.entity.User;

// A.1 - Create a UserRepository interface that extends CrudRepository<User, Long>. This repository will provide basic CRUD operations for the User entity.
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    // Custom Function 
    @Query("SELECT COUNT(u) FROM User u JOIN u.authorities a WHERE a.authority = 'ROLE_ADMIN'")
    long countAdminUsers();
}
