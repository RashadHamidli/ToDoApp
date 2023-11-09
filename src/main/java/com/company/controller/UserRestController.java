package com.company.controller;

import com.company.dto.UserDTO;
import com.company.request.UserLoginRequest;
import com.company.request.UserRequest;
import com.company.respons.UserRespons;
import com.company.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserRestController {
    private final UserService userService;
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserRespons> login(@RequestBody UserLoginRequest request) {
        UserRespons userRespons = userService.loginUser(request);
        return userRespons != null ? ResponseEntity.ok(userRespons) : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRequest userRequest) {
        boolean user1 = userService.createUser(userRequest);
        return user1 ? ResponseEntity.status(HttpStatus.CREATED).body("create user successfully")
                : ResponseEntity.status(HttpStatus.CONFLICT).body("email is alredy exsist");
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshUser() {
        return null;
    }

    @GetMapping
    public ResponseEntity<List<UserRespons>> getAllUsers() {
        List<UserRespons> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        boolean updateUser = userService.updateUser(userId, userDTO);
        return updateUser ? ResponseEntity.status(HttpStatus.OK).body("update user successfully")
                : ResponseEntity.status(HttpStatus.CONFLICT).body("user cannot be updated");
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        return null;
    }

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<UserDTO> getUserTasks(@PathVariable Long userId) {
        return null;
    }
}
