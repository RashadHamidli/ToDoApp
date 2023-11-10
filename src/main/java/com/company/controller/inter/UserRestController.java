package com.company.controller.inter;

import com.company.dto.request.TaskRequest;
import com.company.dto.request.UserRequest;
import com.company.dto.response.TaskRespons;
import com.company.dto.response.UserRespons;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface UserRestController {

    ResponseEntity<List<UserRespons>> getAllUsers();

    ResponseEntity<UserRespons> updateUser(@PathVariable Long userId,
                                           @RequestBody UserRequest userRequest);

    ResponseEntity<String> deleteUser(@PathVariable Long userId);

    ResponseEntity<List<TaskRespons>> getUserTasks(@PathVariable Long userId);

    ResponseEntity<TaskRespons> updateUserTaskByUserIdAndTaskId(@PathVariable Long userId,
                                                                @PathVariable Long tasksid,
                                                                @RequestBody TaskRequest taskRequest);

}
