package com.qa.lab4.controller;

import com.qa.lab4.model.Task;
import com.qa.lab4.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    private static final String TASKS_PATH = "/tasks";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
    }

    @Test
    void getAll_Success() throws Exception {
        when(taskService.getAll()).thenReturn(List.of(task));

        mockMvc.perform(get(TASKS_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));

        verify(taskService, times(1)).getAll();
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void getById_Success() throws Exception {
        when(taskService.getById(1L)).thenReturn(task);

        mockMvc.perform(get(TASKS_PATH + "/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(taskService, times(1)).getById(1L);
        verifyNoMoreInteractions(taskService);
    }

}
