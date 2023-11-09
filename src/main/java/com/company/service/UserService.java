package com.company.service;

import com.company.dto.TaskDTO;
import com.company.dto.UserDTO;
import com.company.entity.Task;
import com.company.entity.User;
import com.company.mapper.TaskMapper;
import com.company.mapper.UserMapper;
import com.company.reposiroty.UserRepository;
import com.company.request.UserLoginRequest;
import com.company.request.UserRequest;
import com.company.respons.TaskRespons;
import com.company.respons.UserRespons;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskMapper taskMapper;
    private final UserLoginRequest userLoginRequest;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       TaskMapper taskMapper, UserLoginRequest userLoginRequest) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.taskMapper = taskMapper;
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


//    public List<UserDTO> getAllUser2() {
//        List<User> allUsers = userRepository.findAll();
//        return allUsers.stream().map(user -> {
//            UserDTO userDTO = userMapper.userDTOConvertToUser(user);
//            List<TaskDTO> taskDTOS = user.getTaskList()
//                    .stream()
//                    .map(taskMapper::taskConvertToTaskDTO)
//                    .collect(Collectors.toList());
//            userDTO.setTaskList(taskDTOS);
//            return userDTO;
//        }).toList();
//    }


    public boolean updateUser(Long userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User foundedUser = optionalUser.get();
            if (userDTO.getName() != null && !userDTO.getName().isEmpty())
                foundedUser.setName(userDTO.getName());
            if (userDTO.getSurname() != null && !userDTO.getSurname().isEmpty())
                foundedUser.setSurname(userDTO.getSurname());
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


}
