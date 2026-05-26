package com.irenejoy15.springboot.todos.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irenejoy15.springboot.todos.entity.User;

// A.1 - Create a UserRepository interface that extends CrudRepository<User, Long>. This repository will provide basic CRUD operations for the User entity.
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
