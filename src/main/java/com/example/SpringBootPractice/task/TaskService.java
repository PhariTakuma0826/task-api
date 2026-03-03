package com.example.SpringBootPractice.task;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class TaskService {

    private final TaskRepository trepo;
    private final UserRepository urepo;

    public TaskService(TaskRepository trepo, UserRepository urepo) {
        this.trepo = trepo;
        this.urepo = urepo;
    }

    public List<Task> findAll() {
        return trepo.findAll();
    }

    public Task create(String title, Priority priority) {

        User user = urepo.findById(1L)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        Task task = new Task(title, priority);
        task.setUser(user);

        return trepo.save(task);
    }

    public Task update(long id, String title, Boolean completed) {
        Task t = trepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        t.update(title, completed);
        return trepo.save(t);
    }

    public void delete(long id) {
        Task t = trepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        trepo.delete(t);
    }
    public Task toggleCompleted(Long id) {
        Task t = trepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        t.update(null, !t.isCompleted()); // 反転
        return trepo.save(t);
    }

    public Task updateTitle(long id, String title) {
        Task t = trepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));

        t.updateTitle(title);   // ← 次でTaskに作る
        return trepo.save(t);    // 確実にDB反映（今はこれでOK）
    }

    public Page<Task> findAll(Pageable pageable) {
        return trepo.findAllByOrderByPriorityRankDescCreatedAtDesc(pageable);
    }

    public Page<Task> findIncomplete(Pageable pageable) {
        return trepo.findByCompletedFalseOrderByPriorityRankDescCreatedAtDesc(pageable);
    }

    public Page<Task> findComplete(Pageable pageable) {
        return trepo.findByCompletedTrueOrderByPriorityRankDescCreatedAtDesc(pageable);
    }

    public Page<Task> search(String q, Pageable pageable) {
        return trepo.findByTitleContainingIgnoreCaseOrderByPriorityRankDescCreatedAtDesc(q, pageable);
    }

        public Task findById(Long id) {
        return trepo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }
}