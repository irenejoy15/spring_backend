package com.irenejoy15.springboot.todos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.irenejoy15.springboot.todos.entity.Todo;
import com.irenejoy15.springboot.todos.entity.User;
import com.irenejoy15.springboot.todos.repository.TodoRepository;
import com.irenejoy15.springboot.todos.request.TodoRequest;
import com.irenejoy15.springboot.todos.response.TodoResponse;
import com.irenejoy15.springboot.todos.util.FindAuthenticatedUser;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
       return convertToTodoResponse(savedTodo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTodos() {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        return todoRepository.findByOwnerId(currentUser)
            .stream()
            .map(this::convertToTodoResponse)
            .toList();

    }

    private TodoResponse convertToTodoResponse(Todo todo) {
        return new TodoResponse(
            todo.getId(),
            todo.getTitle(),
            todo.getDescription(),
            todo.getPriority(),
            todo.isComplete()
        );
    }

    @Override
    public TodoResponse toggleTodoCompletion(Long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);

        if(todo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        };

        todo.get().setComplete(!todo.get().isComplete());
        Todo updatedTodo = todoRepository.save(todo.get());
        return convertToTodoResponse(updatedTodo);
    }

    @Transactional
    public void deleteTodo(Long id) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();
        Optional<Todo> todo = todoRepository.findByIdAndOwner(id, currentUser);

        if(todo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        };

        todoRepository.delete(todo.get());        
    }
}
