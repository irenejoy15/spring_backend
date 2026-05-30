package com.irenejoy15.springboot.todos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TodoRequest {

    @NotEmpty(message = "Title is required")
    @Size(min=3, max = 30, message = "Title must be between 3 and 30 characters")
    private String title;
    
    @NotEmpty(message = "Description is required")
    @Size(min=5, max = 100, message = "Description must be between 5 and 100 characters")
    private String description;

    @Min(value = 1, message = "Priority must be at least 1")
    @Max(value = 5, message = "Priority must be at most 5")
    private int priority;

    public TodoRequest(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }

    
}
