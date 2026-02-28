package com.example.SpringBootPractice.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.example.SpringBootPractice.task.Priority;
import jakarta.validation.constraints.NotNull;

public class TaskCreateForm {

    @NotBlank(message = "タイトルは必須です")
    @Size(max = 50, message = "タイトルは50文字以内にしてください")
    private String title;

    public String getTitle() {
        return title;

    }
    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull(message = "優先度を選んでください")
    private Priority priority = Priority.MEDIUM;

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}