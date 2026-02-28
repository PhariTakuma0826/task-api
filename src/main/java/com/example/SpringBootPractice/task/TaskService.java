package com.example.SpringBootPractice.task;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public List<Task> findAll() {
        return repo.findAll();
    }

    public Task create(String title, Priority priority) {
        Task task = new Task(title, priority);
        return repo.save(task);
    }

    public Task update(long id, String title, Boolean completed) {
        Task t = repo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        t.update(title, completed);
        return repo.save(t);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }

    public Task toggleCompleted(Long id) {
        Task t = repo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        t.update(null, !t.isCompleted()); // 反転
        return repo.save(t);
    }

    public Task updateTitle(long id, String title) {
        Task t = repo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        t.updateTitle(title);   // ← 次でTaskに作る
        return repo.save(t);    // 確実にDB反映（今はこれでOK）
    }

    public Page<Task> findAll(Pageable pageable) {
        return repo.findAllByOrderByPriorityRankDescCreatedAtDesc(pageable);
    }

    public Page<Task> findIncomplete(Pageable pageable) {
        return repo.findByCompletedFalseOrderByPriorityRankDescCreatedAtDesc(pageable);
    }

    public Page<Task> findComplete(Pageable pageable) {
        return repo.findByCompletedTrueOrderByPriorityRankDescCreatedAtDesc(pageable);
    }

    public Page<Task> search(String q, Pageable pageable) {
        return repo.findByTitleContainingIgnoreCaseOrderByPriorityRankDescCreatedAtDesc(q, pageable);
    }

        public Task findById(Long id) {
        return repo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }
}