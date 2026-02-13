package com.example.SpringBootPractice.task;

public class UpdateTaskRequest {
    private boolean completed;
    private String title;

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
