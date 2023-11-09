package com.company.service;

import com.company.dao.entity.Task;
import com.company.dao.entity.User;
import com.company.dao.reposiroty.UserRepository;
import com.company.dto.request.UserLoginRequest;
import com.company.dto.request.UserRequest;
import com.company.dto.respons.TaskRespons;
import com.company.dto.respons.UserRespons;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserLoginRequest userLoginRequest;

    public UserService(UserRepository userRepository, UserLoginRequest userLoginRequest) {
        this.userRepository = userRepository;
        this.userLoginRequest = userLoginRequest;
    }

    public boolean createUser(UserRequest userRequest) {
        User foundedUser = userRepository.findUserByEmail(userRequest.getEmail());
        if (foundedUser != null)
            return false;
        User user = userRequest.userRequestConvertToUser(userRequest);
        userRepository.save(user);
        return true;
    }

    public UserRespons loginUser(UserLoginRequest request) {
        User user = userLoginRequest.userLoginRequestConvertToUser(request);
        User foundedUser = userRepository.findUserByEmail(user.getEmail());
        if (foundedUser != null
                && foundedUser.getEmail().equals(user.getEmail())
                && foundedUser.getPassword().equals(user.getPassword())) {
            List<Task> userTasks = foundedUser.getTaskList();
            List<TaskRespons> tasks = userTasks.stream()
                    .map(TaskRespons::new)
                    .collect(Collectors.toList());
            return new UserRespons(foundedUser, tasks);
        }
        return null;
    }

    public List<UserRespons> getAllUser() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(user -> {
            List<TaskRespons> tasks = user.getTaskList().stream()
                    .map(TaskRespons::new)
                    .collect(Collectors.toList());

            return new UserRespons(user, tasks);
        }).collect(Collectors.toList());
    }

    public UserRespons updateUser(Long userId, UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User foundedUser = optionalUser.get();
            if (userRequest.getName() != null && !userRequest.getName().isEmpty())
                foundedUser.setName(userRequest.getName());
            if (userRequest.getSurname() != null && !userRequest.getSurname().isEmpty())
                foundedUser.setSurname(userRequest.getSurname());
            foundedUser.setId(userId);
            User updateUser = userRepository.save(foundedUser);
            List<TaskRespons> tasks = optionalUser.get().getTaskList().stream()
                    .map(TaskRespons::new)
                    .collect(Collectors.toList());
            List<Task> taskList = optionalUser.get().getTaskList();
            return new UserRespons(updateUser, tasks);
        }
        return null;
    }

    public boolean deleteUser(Long userId) {
        Optional<User> optionalUser = Optional.of(userRepository.findById(userId).orElseThrow());
        userRepository.delete(optionalUser.get());
        return true;
    }

    public List<TaskRespons> getUserTasks(Long userId) {
        Optional<User> foundedUser = Optional.of(userRepository.findById(userId).orElseThrow());
        List<Task> taskList = foundedUser.get().getTaskList();
        List<TaskRespons> tasks = taskList.stream().map(TaskRespons::new).collect(Collectors.toList());
        return tasks;
    }
}
