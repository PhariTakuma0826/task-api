package com.example.SpringBootPractice.task;

import jakarta.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean completed;

    protected Task() {
        // JPA用
    }

    public Task(String title) {
        this.title = title;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void update(String title, Boolean completed) {
        if (title != null) {
            this.title = title;
        }
        if (completed != null) {
            this.completed = completed;
        }
    }
}