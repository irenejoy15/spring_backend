package com.irenejoy15.springboot.todos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irenejoy15.springboot.todos.entity.User;
import com.irenejoy15.springboot.todos.request.PasswordUpdateRequest;
import com.irenejoy15.springboot.todos.response.UserResponse;
import com.irenejoy15.springboot.todos.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

@Tag(name = "User Controller", description = "APIs for user current user information")
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get current user information", description = "Endpoint to retrieve the current authenticated user's information")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public UserResponse getUserInfo(){
        return userService.getUserInfo();
    }

    // 6.4 - Add a new endpoint to the UserController class that allows users to delete their accounts. This endpoint should be mapped to a DELETE request and should call the deleteUser method of the UserService to perform the account deletion logic. Ensure that appropriate security measures are in place to prevent unauthorized access to this endpoint, such as requiring authentication and verifying that the user requesting the deletion is the owner of the account being deleted.  
    @Operation(summary = "Delete current user account", description = "Endpoint to delete the current authenticated user's account")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    public void deleteUser() {
        userService.deleteUser();
    }

    @Operation(summary = "Delete user by ID", description = "Endpoint to delete a user by their ID")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
    }

    @Operation(summary = "Update current user password", description = "Endpoint to update the current authenticated user's password")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/password")
    public void passwordUpdate(@Valid @RequestBody PasswordUpdateRequest passwordUpdateRequest) throws Exception {
        userService.updatePassword(passwordUpdateRequest);
    }
}
