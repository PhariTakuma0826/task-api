package com.example.SpringBootPractice.task;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;

import java.util.List;

@Controller
public class TaskPageController {
    private final TaskService taskService;

    public TaskPageController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String list(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String q,
            @PageableDefault(size = 5) Pageable pageable,
            Model model) {

        Page<Task> page;

        if (q != null && !q.isBlank()) {
            page = taskService.search(q, pageable);
        } else if ("done".equals(filter)) {
            page = taskService.findComplete(pageable);
        } else if ("todo".equals(filter)) {
            page = taskService.findIncomplete(pageable);
        } else {
            page = taskService.findAll(pageable);
        }

        model.addAttribute("page", page);
        model.addAttribute("tasks", page.getContent()); // 既存HTMLを活かす用
        model.addAttribute("filter", filter);
        model.addAttribute("q", q);
        model.addAttribute("priorities", Priority.values());

        // もし追加フォーム使ってるなら
        model.addAttribute("taskForm", new TaskCreateForm());

        return "index";
    }

    @PostMapping("/tasks")
    public String addTask(@Valid @ModelAttribute("taskForm") TaskCreateForm form,
                          BindingResult bindingResult,
                          @RequestParam(required = false) String filter,
                          @RequestParam(required = false) String q,
                          @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                          Model model) {

        if (bindingResult.hasErrors()) {
            // list() と同じロジックで詰め直す
            return list(filter, q, pageable, model);
        }

        taskService.create(form.getTitle(), form.getPriority());
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/toggle")
    public String toggle(@PathVariable Long id) {
        taskService.toggleCompleted(id);
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/edit")
    public String editTitle(@PathVariable long id, @RequestParam String title) {
        taskService.updateTitle(id, title);
        return "redirect:/";
    }

    @GetMapping("/tasks/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id); // 見つからなければTaskNotFoundException
        model.addAttribute("task", task);
        return "task-detail";
    }
}