package com.company.service.impl;

import com.company.dao.entities.Task;
import com.company.dao.repository.TaskRepository;
import com.company.dao.repository.UserRepository;
import com.company.dto.request.TaskRequest;
import com.company.dto.response.TaskRespons;
import com.company.exceptions.MyExceptionHandler;
import com.company.service.inter.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<TaskRespons> getAllTasks() {
        return taskRepository.findAll().stream().map(TaskRespons::new).collect(Collectors.toList());
    }
    @Override
    public TaskRespons getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        return new TaskRespons(task);
    }
    @Override
    public TaskRespons createTask(TaskRequest taskRequest) {
        Task task = new TaskRequest().taskRequestConverToTask(taskRequest);
        Task savedTask = taskRepository.save(task);
        return new TaskRespons(savedTask);
    }
    @Override
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
    @Override
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
    @Override
    public boolean deleteTaskByTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(MyExceptionHandler::new);
        taskRepository.delete(task);
        return true;
    }
    @Override
    public boolean deleteTaskByUserIdAndTaskId(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(MyExceptionHandler::new);
        if(task.getUser().getId().equals(userId)){
            taskRepository.delete(task);
            return true;
        }
        return false;
    }
}
