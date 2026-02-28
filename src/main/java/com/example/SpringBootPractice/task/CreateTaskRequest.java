package com.example.SpringBootPractice.task;

public class CreateTaskRequest {

    private String title;
    private Priority priority;

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}