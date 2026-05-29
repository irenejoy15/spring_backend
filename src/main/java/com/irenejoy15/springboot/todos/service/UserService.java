package com.irenejoy15.springboot.todos.service;

import com.irenejoy15.springboot.todos.response.UserResponse;

public interface UserService {
    UserResponse getUserInfo();
    // 6.1 - Add a deleteUser method to the UserService interface that will handle the logic for deleting a user account. This method should be designed to be called by the UserController when a user requests to delete their account. The implementation of this method will involve removing the user's data from the database and ensuring that any associated resources (such as tasks or authorities) are also properly handled.
    void deleteUser();
    void deleteUserById(Long userId);
}
