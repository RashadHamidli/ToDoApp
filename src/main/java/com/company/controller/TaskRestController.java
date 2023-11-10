package com.company.controller;

import com.company.dto.request.TaskRequest;
import com.company.dto.response.TaskRespons;
import com.company.service.impl.TaskServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {
    private final TaskServiceImpl taskServiceImpl;

    public TaskRestController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskRespons>> getAllTasks() {
        List<TaskRespons> allTasks = taskServiceImpl.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskRespons> getTaskByTaskId(@PathVariable Long taskId) {
        TaskRespons taskRespons = taskServiceImpl.getTaskById(taskId);
        return ResponseEntity.ok(taskRespons);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<TaskRespons> createTaskForUserId(@PathVariable Long userId, @RequestBody TaskRequest taskRequest) {
        TaskRespons taskRespons = taskServiceImpl.createTaskForUser(userId, taskRequest);
        return ResponseEntity.ok(taskRespons);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskRespons> updateTaskByTaskId(@PathVariable Long taskId, @RequestBody TaskRequest taskRequest) {
        TaskRespons taskRespons = taskServiceImpl.updateTaskByTaskId(taskId, taskRequest);
        return ResponseEntity.ok(taskRespons);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTaskByTaskId(@PathVariable Long taskId) {
        boolean deletedTask = taskServiceImpl.deleteTaskByTaskId(taskId);
        return deletedTask ? ResponseEntity.status(HttpStatus.OK).body("delete task successfully")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("task cannot be deleted");
    }

}
