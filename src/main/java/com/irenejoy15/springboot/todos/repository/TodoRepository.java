package com.irenejoy15.springboot.todos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irenejoy15.springboot.todos.entity.Todo;
import com.irenejoy15.springboot.todos.entity.User;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByOwnerId(User owner);
    Optional<Todo> findByIdAndOwner(Long id, User owner);
}
