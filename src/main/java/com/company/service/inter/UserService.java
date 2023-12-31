package com.company.service.inter;

import com.company.dao.entities.User;
import com.company.model.dto.request.TaskRequest;
import com.company.model.dto.request.UserRequest;
import com.company.model.dto.response.TaskRespons;
import com.company.model.dto.response.UserRespons;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService userDetailsService();

    List<UserRespons> getAllUser();

    UserRespons updateUser(Long userId, UserRequest userRequest);

    boolean deleteUser(Long userId);

    List<TaskRespons> getUserTasks(Long userId);

    TaskRespons updateUserTasks(Long userId, Long taskId, TaskRequest taskRequest);
}
