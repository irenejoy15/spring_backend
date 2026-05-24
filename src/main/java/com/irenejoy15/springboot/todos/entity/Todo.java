package com.irenejoy15.springboot.todos.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "todos")
@Entity
// 1. Create a Todo entity class with fields
public class Todo {

    // 5. Add @Id and @GeneratedValue annotations to the id field
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int priority;
    
    @Column(nullable = false)
    private boolean complete;

    // One User have many Todos
    // private User owner;

    // Default constructor (required by JPA)
    public Todo() {}
    // 2nd constructor for creating new Todo objects
    public Todo(String title, String description, int priority, boolean complete) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.complete = complete;
    }
    // 4th generate getters and setters for all fields
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    
}

