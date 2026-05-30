package com.irenejoy15.springboot.todos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.irenejoy15.springboot.todos.entity.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

}
