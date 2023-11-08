package com.company.mapper;

import com.company.dto.TaskDTO;
import com.company.dto.UserDTO;
import com.company.entity.Task;
import com.company.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private final TaskMapper taskMapper;

    public UserMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public UserDTO userDTOConvertToUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setEmail(user.getEmail());
        userDTO.setTaskList(user.getTaskList().stream().map(taskMapper::taskConvertToTaskDTO).collect(Collectors.toList()));
        return userDTO;
    }

    public User userConvertToUserDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setTaskList(userDTO.getTaskList().stream().map(taskMapper::taskDTOConvertToTask).collect(Collectors.toList()));
        return user;
    }
}
