package com.company.controller;

import com.company.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestBody UserDTO userDTO) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        return null;
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return null;
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
