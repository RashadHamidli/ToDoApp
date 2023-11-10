package com.company.controller.impl;

import com.company.controller.inter.UserRestController;
import com.company.dto.request.TaskRequest;
import com.company.dto.request.UserRequest;
import com.company.dto.response.TaskRespons;
import com.company.dto.response.UserRespons;
import com.company.service.inter.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/todoapp/users")
public class UserRestControllerImpl implements UserRestController {
    private final UserService userService;

    public UserRestControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/all")
    @Secured("ADMIN")
    public ResponseEntity<List<UserRespons>> getAllUsers() {
        List<UserRespons> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @Override
    @PutMapping("/update/{userId}")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<UserRespons> updateUser(@PathVariable Long userId,
                                                  @RequestBody UserRequest userRequest) {
        UserRespons userRespons = userService.updateUser(userId, userRequest);
        return userRespons != null ? ResponseEntity.status(HttpStatus.OK).body(userRespons)
                : ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @Override
    @DeleteMapping("delete/{userId}")
    @Secured("ADMIN")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        boolean deleteUser = userService.deleteUser(userId);
        return deleteUser ? ResponseEntity.status(HttpStatus.OK).body("delete successfully")
                : ResponseEntity.status(HttpStatus.CONFLICT).body("user cannot deleted");
    }

    @Override
    @GetMapping("/{userId}/tasks")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<List<TaskRespons>> getUserTasks(@PathVariable Long userId) {
        List<TaskRespons> userTasks = userService.getUserTasks(userId);
        return ResponseEntity.ok(userTasks);
    }

    @Override
    @PostMapping("/{userId}/{tasksid}")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<TaskRespons> updateUserTaskByUserIdAndTaskId(@PathVariable Long userId,
                                                                       @PathVariable Long tasksid,
                                                                       @RequestBody TaskRequest taskRequest) {
        TaskRespons taskRespons = userService.updateUserTasks(userId, tasksid, taskRequest);
        return ResponseEntity.ok(taskRespons);
    }
}
