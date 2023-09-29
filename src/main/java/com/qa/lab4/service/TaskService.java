package com.qa.lab4.service;

import com.qa.lab4.dto.request.CreateTaskRequest;
import com.qa.lab4.error.ResourceNotFoundEx;
import com.qa.lab4.model.Task;
import com.qa.lab4.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public void createTask(CreateTaskRequest request) {
        taskRepository.createTask(request);
    }

    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    public Task getById(Long id) {
        Optional<Task> opTask = taskRepository.getById(id);
        if (opTask.isEmpty()) {
            throw new ResourceNotFoundEx("Task with id " + id + " not found");
        }

        return opTask.get();
    }

    @SneakyThrows
    public String easterEgg(String data) {
        File file = new File("./" + data);
        file.mkdir();
        return "Easter egg created";
    }

}
