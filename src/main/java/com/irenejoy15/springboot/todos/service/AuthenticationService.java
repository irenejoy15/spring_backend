package com.irenejoy15.springboot.todos.service;

import com.irenejoy15.springboot.todos.request.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
}
