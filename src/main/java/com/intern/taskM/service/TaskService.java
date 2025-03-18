// TaskService.java
package com.intern.taskM.service;

import com.intern.taskM.model.Priority;
import com.intern.taskM.model.Status;
import com.intern.taskM.model.Task;
import com.intern.taskM.repository.TaskRepository;
import com.intern.taskM.util.PriorityTaskScheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final PriorityTaskScheduler taskScheduler;
    
    public Task createTask(Task task) {
        Task savedTask = taskRepository.save(task);
        taskScheduler.addTask(savedTask);
        return savedTask;
    }
    
    public Optional<Task> updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            task.setPriority(taskDetails.getPriority());
            return taskRepository.save(task);
        });
    }
    
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
    
    public Optional<Task> getTask(Long id) {
        return taskRepository.findById(id);
    }
    
    @Cacheable(value = "tasks", key = "#pageable.pageNumber + '-' + (#priority != null ? #priority.toString() : 'all') + '-' + (#status != null ? #status.toString() : 'all')")
    public Page<Task> getTasks(Priority priority, Status status, Pageable pageable) {
        if (priority != null && status != null) {
            return taskRepository.findByPriorityAndStatus(priority, status, pageable);
        } else if (priority != null) {
            return taskRepository.findByPriority(priority, pageable);
        } else if (status != null) {
            return taskRepository.findByStatus(status, pageable);
        } else {
            return taskRepository.findAll(pageable);
        }
    }
    
    public Task getNextScheduledTask() {
        return taskScheduler.getNextTask();
    }
}
