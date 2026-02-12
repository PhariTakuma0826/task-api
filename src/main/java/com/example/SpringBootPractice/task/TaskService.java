package com.example.SpringBootPractice.task;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Task> findAll() {
        return tasks;
    }

    public Task create(String title) {
        Task task = new Task(idGenerator.getAndIncrement(), title, false);
        tasks.add(task);
        return task;
    }
}