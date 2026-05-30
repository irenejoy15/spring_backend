package com.irenejoy15.springboot.todos.util;

import com.irenejoy15.springboot.todos.entity.User;

public interface FindAuthenticatedUser {
    User getAuthenticatedUser();
}
