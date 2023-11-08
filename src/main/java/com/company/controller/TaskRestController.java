//package com.company.controller;
//
//import com.company.dto.TaskDTO;
//import com.company.service.TaskService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskRestController {
//    private final TaskService taskService;
//
//    public TaskRestController(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<TaskDTO>> getAllTasks() {
//        return taskService.getAllTasks();
//    }
//
//    @GetMapping("/{taskId}")
//    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long taskId) {
//        return taskService.getTaskById(taskId);
//    }
//
//    @PostMapping("/user/{userId}")
//    public ResponseEntity<TaskDTO> createTaskForUser(@PathVariable Long userId, @RequestBody TaskDTO taskDTO) {
//        return taskService.createTaskForUser(userId, taskDTO);
//    }
//
//    @PutMapping("/{taskId}")
//    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
//        return taskService.updateTask(taskId, taskDTO);
//    }
//
//    @DeleteMapping("/{taskId}")
//    public void deleteTask(@PathVariable Long taskId) {
//        taskService.deleteTask(taskId);
//    }
//
//}
