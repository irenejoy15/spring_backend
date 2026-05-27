package com.irenejoy15.springboot.todos.service;

import com.irenejoy15.springboot.todos.request.AuthenticationRequest;
import com.irenejoy15.springboot.todos.request.RegisterRequest;
import com.irenejoy15.springboot.todos.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest input) throws Exception;
}
