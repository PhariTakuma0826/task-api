package com.example.SpringBootPractice.task;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks() {
        return taskService.findAll();
    }

    @PostMapping
    public Task createTask(@RequestBody CreateTaskRequest request) {
        return taskService.create(request.getTitle());
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable long id, @RequestBody UpdateTaskRequest request) {
        return taskService.update(id, request.getTitle(), request.getCompleted());
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable long id) {
        taskService.delete(id);
    }
}