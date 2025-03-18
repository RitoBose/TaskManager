// TaskServiceTest.java
package com.intern.taskM;

import com.intern.taskM.model.Priority;
import com.intern.taskM.model.Status;
import com.intern.taskM.model.Task;
import com.intern.taskM.repository.TaskRepository;
import com.intern.taskM.service.TaskService;
import com.intern.taskM.util.PriorityTaskScheduler;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import java.util.Collections;
//import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    
    @Mock
    private PriorityTaskScheduler taskScheduler;
    
    @InjectMocks
    private TaskService taskService;
    
    @Test
    public void testCreateTask() {
        Task task = Task.builder()
                        .title("Test Task")
                        .description("Test description")
                        .priority(Priority.HIGH)
                        .status(Status.PENDING)
                        .build();
                        
        when(taskRepository.save(task)).thenReturn(task);
        
        Task created = taskService.createTask(task);
        assertEquals("Test Task", created.getTitle());
        verify(taskScheduler, times(1)).addTask(task);
    }
    
    @Test
    public void testGetTasks() {
        Task task = Task.builder().title("Sample Task").build();
        Page<Task> page = new PageImpl<>(Collections.singletonList(task));
        when(taskRepository.findAll(PageRequest.of(0, 10))).thenReturn(page);
        
        Page<Task> result = taskService.getTasks(null, null, PageRequest.of(0, 10));
        assertEquals(1, result.getTotalElements());
    }
}
