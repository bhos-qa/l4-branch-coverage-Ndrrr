package com.qa.lab4.service;

import com.qa.lab4.dto.request.CreateTaskRequest;
import com.qa.lab4.error.ResourceNotFoundEx;
import com.qa.lab4.model.Task;
import com.qa.lab4.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
    }

    @Test
    void createTask_Success() {
        var given = new CreateTaskRequest();
        given.setId(1L);
        given.setName("name");
        given.setDone(Boolean.FALSE);

        doNothing().when(taskRepository).createTask(given);

        taskService.createTask(given);

        verify(taskRepository, times(1)).createTask(given);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void getAll_Success() {
        var expected = List.of(task);

        when(taskRepository.getAll()).thenReturn(expected);

        List<Task> actual = taskService.getAll();

        assertEquals(expected, actual);
        verify(taskRepository, times(1)).getAll();
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void getById_Success() {
        var expected = task;

        when(taskRepository.getById(1L)).thenReturn(java.util.Optional.of(expected));

        Task actual = taskService.getById(1L);

        assertEquals(expected, actual);
        verify(taskRepository, times(1)).getById(1L);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    void getById_WhenTaskNotFound() {
        when(taskRepository.getById(1L)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundEx.class, () -> taskService.getById(1L));
        verify(taskRepository, times(1)).getById(1L);
        verifyNoMoreInteractions(taskRepository);
    }

}
