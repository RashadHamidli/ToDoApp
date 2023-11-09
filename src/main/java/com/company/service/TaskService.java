package com.company.service;

import com.company.entity.Task;
import com.company.entity.User;
import com.company.reposiroty.TaskRepository;
import com.company.reposiroty.UserRepository;
import com.company.request.TaskRequest;
import com.company.respons.TaskRespons;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Task task = new TaskRequest().taskRequestConverToTask(taskRequest);
            User user = optionalUser.get();
            List<Task> userTasks = user.getTaskList();
            userTasks.add(task);
            task.setUser(user);
            taskRepository.save(task);
            userRepository.save(user);
            return new TaskRespons(task);
        }
        return null;
    }

    public TaskRespons updateTaskByTaskId(Long taskId, TaskRequest taskRequest) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            if (taskRequest.getTaskName() != null && !taskRequest.getTaskName().isEmpty())
                task.setTaskName(taskRequest.getTaskName());
            if (taskRequest.getDeadline() != null)
                task.setDedline(taskRequest.getDeadline());
            if (taskRequest.getText() != null && !taskRequest.getText().isEmpty())
                task.setText(taskRequest.getText());
            Task savedTask = taskRepository.save(task);
            return new TaskRespons(savedTask);
        }
        return null;
    }

    public boolean deleteTaskByTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        taskRepository.delete(task);
        return true;
    }
}
