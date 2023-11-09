package com.company.controller;

import com.company.dto.TaskDTO;
import com.company.request.TaskRequest;
import com.company.respons.TaskRespons;
import com.company.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskRespons>> getAllTasks() {
        List<TaskRespons> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskRespons> getTaskById(@PathVariable Long taskId) {
        TaskRespons taskRespons = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskRespons);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<TaskRespons> createTaskForUser(@PathVariable Long userId, @RequestBody TaskRequest taskRequest) {
        return taskService.createTaskForUser(userId, taskRequest);
    }
//
//    @PutMapping("/{taskId}")
//    public ResponseEntity<TaskRespons> updateTask(@PathVariable Long taskId, @RequestBody TaskRequest taskRequest) {
//        return taskService.updateTask(taskId, taskRequest);
//    }
//
//    @DeleteMapping("/{taskId}")
//    public void deleteTask(@PathVariable Long taskId) {
//        taskService.deleteTask(taskId);
//    }

}
