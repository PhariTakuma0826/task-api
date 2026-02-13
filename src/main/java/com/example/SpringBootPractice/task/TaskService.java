package com.example.SpringBootPractice.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private long nextId = 1;

    public List<Task> findAll() {
        return tasks;
    }

    public Task create(String title) {
        Task task = new Task(nextId++, title, false);
        tasks.add(task);
        return task;
    }

    public Task update(long id, String title, Boolean completed) {
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getId() == id) {
                String newTitle = (title != null) ? title : t.getTitle();
                boolean newCompleted = (completed != null) ? completed : t.isCompleted();
                Task updated = new Task(t.getId(), newTitle, newCompleted);
                tasks.set(i, updated);
                return updated;
            }
        }
        throw new RuntimeException("Task not found: " + id);
    }

    public void delete(long id) {
        tasks.removeIf(t -> t.getId() == id);
    }
}