package com.irenejoy15.springboot.todos.service;

import com.irenejoy15.springboot.todos.request.TodoRequest;
import com.irenejoy15.springboot.todos.response.TodoResponse;

public interface TodoService {
    TodoResponse createTodo(TodoRequest request);
}
