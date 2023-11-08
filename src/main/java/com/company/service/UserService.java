package com.company.service;

import com.company.dto.TaskDTO;
import com.company.dto.UserDTO;
import com.company.entity.Task;
import com.company.entity.User;
import com.company.mapper.TaskMapper;
import com.company.mapper.UserMapper;
import com.company.reposiroty.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, TaskMapper taskMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.taskMapper = taskMapper;
    }

    public List<UserDTO> getAllUser() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(user -> {
            UserDTO userDTO = userMapper.userDTOConvertToUser(user);
            List<TaskDTO> taskDTOS = user.getTaskList()
                    .stream()
                    .map(taskMapper::taskConvertToTaskDTO)
                    .collect(Collectors.toList());
            userDTO.setTaskList(taskDTOS);
            return userDTO;
        }).toList();
    }

    public boolean createUser(UserDTO userDTO) {
        User findByEmail = userRepository.findUserByEmail(userDTO.getEmail());
        if (findByEmail != null)
            return false;
        User user = userMapper.userConvertToUserDTO(userDTO);
        User savedUser = userRepository.save(user);
        userMapper.userDTOConvertToUser(savedUser);
        return true;
    }

    public boolean updateUser(Long userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User foundedUser = optionalUser.get();
            if (userDTO.getName() != null && !userDTO.getName().isEmpty())
                foundedUser.setName(userDTO.getName());
            if (userDTO.getSurname() != null && !userDTO.getSurname().isEmpty())
                foundedUser.setSurname(userDTO.getSurname());
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty())
                foundedUser.setPassword(userDTO.getPassword());
            if (userDTO.getTaskList() != null && userDTO.getTaskList().isEmpty()) {
                List<TaskDTO> taskList = userDTO.getTaskList();
                List<Task> taskListDTO = taskList.stream().map(taskMapper::taskDTOConvertToTask).collect(Collectors.toList());
                foundedUser.setTaskList(taskListDTO);
            }
            foundedUser.setId(userId);
            userRepository.save(foundedUser);
            return true;
        }
        return false;
    }

    public boolean loginUser(UserDTO userDTO) {
        User user = userMapper.userConvertToUserDTO(userDTO);
        User foundedUser = userRepository.findUserByEmail(user.getEmail());
        if (foundedUser != null && foundedUser.getEmail().equals(user.getEmail()))
            return true;
        return false;
    }
}
