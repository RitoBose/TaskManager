package com.intern.taskM.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.intern.taskM.model.Task;

import java.util.PriorityQueue;

@Component
@Slf4j
public class PriorityTaskScheduler {

    private final PriorityQueue<Task> taskQueue;
    
    public PriorityTaskScheduler() {
        this.taskQueue = new PriorityQueue<>((t1, t2) -> {
            int cmp = Integer.compare(t1.getPriority().getOrder(), t2.getPriority().getOrder());
            return cmp != 0 ? cmp : t1.getCreatedAt().compareTo(t2.getCreatedAt());
        });
    }
    
    public void addTask(Task task) {
        taskQueue.offer(task);
        log.info("Task added to scheduler: {}", task);
    }
    
    public Task getNextTask() {
        return taskQueue.poll();
    }
}
