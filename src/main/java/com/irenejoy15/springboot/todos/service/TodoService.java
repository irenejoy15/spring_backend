package com.irenejoy15.springboot.todos.service;

import java.util.List;

import com.irenejoy15.springboot.todos.request.TodoRequest;
import com.irenejoy15.springboot.todos.response.TodoResponse;

public interface TodoService {
    List<TodoResponse> getAllTodos();
    TodoResponse createTodo(TodoRequest request);
}
