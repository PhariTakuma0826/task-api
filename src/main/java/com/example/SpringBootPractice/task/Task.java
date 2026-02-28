package com.example.SpringBootPractice.task;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "task")
public class Task {

    public Task(String title, Priority priority) {
        this.title = title;
        this.completed = false;
        setPriority(priority);
    }

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;

    private int priorityRank = Priority.MEDIUM.getRank();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "タイトルは必須です")
    @Size(max = 50, message = "タイトルは50文字以内にしてください")
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Task() {
        // JPA用
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        syncRank();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
        syncRank();
    }

    public Task(String title) {
        this(title, Priority.MEDIUM);
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

    public void updateTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public int getPriorityRank() {
        return priorityRank;
    }

    public void setPriority(Priority priority) {
        this.priority = (priority == null) ? Priority.MEDIUM : priority;
        this.priorityRank = this.priority.getRank();
    }

    public void updatePriority(Priority priority) {
        setPriority(priority);
        this.updatedAt = LocalDateTime.now();
    }

    private void syncRank() {
        if (this.priority == null) this.priority = Priority.MEDIUM;
        this.priorityRank = this.priority.getRank();
    }
}