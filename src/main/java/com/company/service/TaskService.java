package com.company.service;

import com.company.dao.entity.Task;
import com.company.dao.reposiroty.TaskRepository;
import com.company.dao.reposiroty.UserRepository;
import com.company.dto.request.TaskRequest;
import com.company.dto.response.TaskRespons;
import com.company.exceptions.MyExceptionHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskRespons> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskRespons::new).collect(Collectors.toList());
    }

    public TaskRespons getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        return new TaskRespons(task);
    }

    public TaskRespons createTaskForUser(Long userId, TaskRequest taskRequest) {
        return userRepository.findById(userId)
                .map(user -> {
                    Task task = new TaskRequest().taskRequestConverToTask(taskRequest);
                    task.setUser(user);
                    Task savedTask = taskRepository.save(task);
                    return new TaskRespons(savedTask);
                })
                .orElseThrow(MyExceptionHandler::new);
    }

    public TaskRespons updateTaskByTaskId(Long taskId, TaskRequest taskRequest) {
        return taskRepository.findById(taskId)
                .map(task -> {
                    if (taskRequest.getTaskName() != null && !taskRequest.getTaskName().isEmpty())
                        task.setTaskName(taskRequest.getTaskName());
                    if (taskRequest.getDeadline() != null)
                        task.setDedline(taskRequest.getDeadline());
                    if (taskRequest.getText() != null && !taskRequest.getText().isEmpty())
                        task.setText(taskRequest.getText());
                    Task savedTask = taskRepository.save(task);
                    return new TaskRespons(savedTask);
                })
                .orElseThrow(MyExceptionHandler::new);
    }

    public boolean deleteTaskByTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        taskRepository.delete(task);
        return true;
    }
}
