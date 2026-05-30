package com.irenejoy15.springboot.todos.service;

import org.springframework.stereotype.Service;

import com.irenejoy15.springboot.todos.entity.Todo;
import com.irenejoy15.springboot.todos.entity.User;
import com.irenejoy15.springboot.todos.repository.TodoRepository;
import com.irenejoy15.springboot.todos.request.TodoRequest;
import com.irenejoy15.springboot.todos.response.TodoResponse;
import com.irenejoy15.springboot.todos.util.FindAuthenticatedUser;

import jakarta.transaction.Transactional;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    public TodoServiceImpl(TodoRepository todoRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.todoRepository = todoRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Override
    @Transactional
    public TodoResponse createTodo(TodoRequest todoRequest) {
       User currentUser = findAuthenticatedUser.getAuthenticatedUser();

       Todo todo = new Todo(
        todoRequest.getTitle(),
        todoRequest.getDescription(),
        todoRequest.getPriority(),
        false,
        currentUser
       );
       Todo savedTodo = todoRepository.save(todo);
       TodoResponse todoResponse = new TodoResponse(
        savedTodo.getId(),
        savedTodo.getTitle(),
        savedTodo.getDescription(),
        savedTodo.getPriority(),
        savedTodo.isComplete()
       );
       return todoResponse;
    }
}
