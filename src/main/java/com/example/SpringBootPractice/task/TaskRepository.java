package com.example.SpringBootPractice.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllByOrderByPriorityRankDescCreatedAtDesc(Pageable pageable);

    Page<Task> findByCompletedFalseOrderByPriorityRankDescCreatedAtDesc(Pageable pageable);

    Page<Task> findByCompletedTrueOrderByPriorityRankDescCreatedAtDesc(Pageable pageable);

    Page<Task> findByTitleContainingIgnoreCaseOrderByPriorityRankDescCreatedAtDesc(String q, Pageable pageable);
}