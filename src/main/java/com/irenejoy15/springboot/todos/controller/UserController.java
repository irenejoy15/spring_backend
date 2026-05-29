package com.irenejoy15.springboot.todos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irenejoy15.springboot.todos.entity.User;
import com.irenejoy15.springboot.todos.response.UserResponse;
import com.irenejoy15.springboot.todos.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "User Controller", description = "APIs for user current user information")
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public UserResponse getUserInfo(){
        return userService.getUserInfo();
    }

    // 6.4 - Add a new endpoint to the UserController class that allows users to delete their accounts. This endpoint should be mapped to a DELETE request and should call the deleteUser method of the UserService to perform the account deletion logic. Ensure that appropriate security measures are in place to prevent unauthorized access to this endpoint, such as requiring authentication and verifying that the user requesting the deletion is the owner of the account being deleted.  
    @DeleteMapping("/delete")
    public void deleteUser() {
        userService.deleteUser();
    }
}
