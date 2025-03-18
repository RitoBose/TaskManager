// TaskController.java
package com.intern.taskM.controller;

import com.intern.taskM.model.Priority;
import com.intern.taskM.model.Status;
import com.intern.taskM.model.Task;
import com.intern.taskM.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
    
    @PutMapping("/{id}")
    public Optional<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
    
    @GetMapping("/{id}")
    public Optional<Task> getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }
    
    @GetMapping
    public Page<Task> getTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) Status status) {
        return taskService.getTasks(priority, status, PageRequest.of(page, size));
    }
    
    @GetMapping("/next")
    public Task getNextTask() {
        return taskService.getNextScheduledTask();
    }
}
