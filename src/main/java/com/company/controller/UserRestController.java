package com.company.controller;

import com.company.dto.UserDTO;
import com.company.request.UserLoginRequest;
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

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserRespons> login(@RequestBody UserLoginRequest request) {
        UserRespons userRespons = userService.loginUser(request);
        return ResponseEntity.ok(userRespons);
    }
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
//        boolean loginUser = userService.loginUser(userDTO);
//        return loginUser ? ResponseEntity.status(HttpStatus.OK).body("user successfully login")
//                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("user cannot be logged in");
//    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        boolean user = userService.createUser(userDTO);

        return user ? ResponseEntity.status(HttpStatus.CREATED).body("create user successfully")
                : ResponseEntity.status(HttpStatus.CONFLICT).body("email is alredy exsist");
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
