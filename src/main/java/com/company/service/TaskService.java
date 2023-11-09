package com.company.service;

import com.company.entity.Task;
import com.company.reposiroty.TaskRepository;
import com.company.respons.TaskRespons;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskRespons> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskRespons::new).collect(Collectors.toList());
    }

    public TaskRespons getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        return new TaskRespons(task);
    }
}
