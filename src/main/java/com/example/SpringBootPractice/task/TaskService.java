package com.example.SpringBootPractice.task;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> findAll() {
        return repo.findAll();
    }

    public Task create(String title) {
        return repo.save(new Task(title));
    }

    public Task update(long id, String title, Boolean completed) {
        Task t = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));

        t.update(title, completed);
        return repo.save(t);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}