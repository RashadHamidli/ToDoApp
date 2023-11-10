package com.company.service;

import com.company.dao.entities.Task;
import com.company.dao.entities.User;
import com.company.dto.request.TaskRequest;
import com.company.dto.request.UserRequest;
import com.company.dto.response.TaskRespons;
import com.company.dto.response.UserRespons;
import com.company.exceptions.MyExceptionHandler;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UserService {
    UserDetailsService userDetailsService();

    List<UserRespons> getAllUser();

    UserRespons updateUser(Long userId, UserRequest userRequest);

    boolean deleteUser(Long userId);

    List<TaskRespons> getUserTasks(Long userId);

    TaskRespons updateUserTasks(Long userId, Long taskId, TaskRequest taskRequest);
}
