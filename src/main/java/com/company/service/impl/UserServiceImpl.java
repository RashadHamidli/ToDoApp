package com.company.service.impl;

import com.company.dao.entities.Task;
import com.company.dao.entities.User;
import com.company.dao.repository.TaskRepository;
import com.company.dao.repository.UserRepository;
import com.company.dto.request.TaskRequest;
import com.company.dto.request.UserRequest;
import com.company.dto.response.TaskRespons;
import com.company.dto.response.UserRespons;
import com.company.exceptions.MyExceptionHandler;
import com.company.service.inter.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
       private final TaskRepository taskRepository;

    public UserServiceImpl(UserRepository userRepository,  TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public List<UserRespons> getAllUser() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(user -> {
            List<TaskRespons> tasks = user.getTaskList().stream()
                    .map(TaskRespons::new)
                    .collect(Collectors.toList());

            return new UserRespons(user, tasks);
        }).collect(Collectors.toList());
    }

    @Override
    public UserRespons updateUser(Long userId, UserRequest userRequest) {
        return userRepository.findById(userId)
                .map(foundedUser -> {
                    if (userRequest.getName() != null && !userRequest.getName().isEmpty())
                        foundedUser.setFirstName(userRequest.getName());
                    if (userRequest.getSurname() != null && !userRequest.getSurname().isEmpty())
                        foundedUser.setLastName(userRequest.getSurname());
                    foundedUser.setId(userId);
                    User updateUser = userRepository.save(foundedUser);
                    List<TaskRespons> tasks = foundedUser.getTaskList().stream()
                            .map(TaskRespons::new)
                            .collect(Collectors.toList());
                    return new UserRespons(updateUser, tasks);
                })
                .orElseThrow(MyExceptionHandler::new);
    }

    @Override
    public boolean deleteUser(Long userId) {
        Optional<User> optionalUser = Optional.of(userRepository.findById(userId).orElseThrow());
        userRepository.delete(optionalUser.get());
        return true;
    }

    @Override
    public List<TaskRespons> getUserTasks(Long userId) {
        Optional<User> foundedUser = Optional.of(userRepository.findById(userId).orElseThrow());
        List<Task> taskList = foundedUser.get().getTaskList();
        List<TaskRespons> tasks = taskList.stream().map(TaskRespons::new).collect(Collectors.toList());
        return tasks;
    }

    @Override
    public TaskRespons updateUserTasks(Long userId, Long taskId, TaskRequest taskRequest) {
        return userRepository.findById(userId)
                .map(user -> {
                    Optional<Task> optionalTask = taskRepository.findById(taskId);
                    if (optionalTask.isPresent()) {
                        Task task = optionalTask.get();
                        if (taskRequest.getTaskName() != null && !taskRequest.getTaskName().isEmpty())
                            task.setTaskName(taskRequest.getTaskName());
                        if (taskRequest.getDeadline() != null)
                            task.setDedline(taskRequest.getDeadline());
                        if (taskRequest.getText() != null && !taskRequest.getText().isEmpty())
                            task.setText(taskRequest.getText());
                        task.setUser(user);
                        Task updatedTask = taskRepository.save(task);
                        return new TaskRespons(updatedTask);
                    }
                    return null;
                })
                .orElseThrow(MyExceptionHandler::new);
    }

}
