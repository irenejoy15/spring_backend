package com.irenejoy15.springboot.todos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    // B.2 - Add a field to represent the owner of the todo (e.g., User owner) and annotate it with @ManyToOne to indicate that it's a many-to-one relationship with the User entity
    // One User have many Todos
    @ManyToOne(fetch = FetchType.LAZY) // Specify the fetch type for the relationship
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    // Default constructor (required by JPA)
    public Todo() {}
    // 2nd constructor for creating new Todo objects
    // B.3 - Update the constructor to include the owner field and initialize it when creating new Todo objects 
    public Todo(String title, String description, int priority, boolean complete, User owner) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.complete = complete;
        this.owner = owner;
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
    // B.4 - Generate getters and setters for the owner field to allow access to the owner of the todo
    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }
    
}

