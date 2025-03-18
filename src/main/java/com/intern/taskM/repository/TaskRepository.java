package com.intern.taskM.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.intern.taskM.model.Priority;
import com.intern.taskM.model.Status;
import com.intern.taskM.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByPriorityAndStatus(Priority priority, Status status, Pageable pageable);
    Page<Task> findByPriority(Priority priority, Pageable pageable);
    Page<Task> findByStatus(Status status, Pageable pageable);
}
