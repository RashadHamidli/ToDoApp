package com.company.controller;

import com.company.dto.request.TaskRequest;
import com.company.dto.response.TaskRespons;
import com.company.service.impl.TaskServiceImpl;
import com.company.service.inter.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todoapp/tasks")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskRespons>> getAllTasks() {
        List<TaskRespons> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{taskId}")
    @Secured("ADMIN")
    public ResponseEntity<TaskRespons> getTaskByTaskId(@PathVariable Long taskId) {
        TaskRespons taskRespons = taskService.getTaskById(taskId);
        return ResponseEntity.ok(taskRespons);
    }

    @PostMapping("/task")
    @Secured("ADMIN")
    public ResponseEntity<TaskRespons> createTask(@RequestBody TaskRequest taskRequest) {
        TaskRespons taskRespons = taskService.createTask(taskRequest);
        return ResponseEntity.ok(taskRespons);
    }

    @PostMapping("/user/{userId}")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<TaskRespons> createTaskForUserId(@PathVariable Long userId,
                                                           @RequestBody TaskRequest taskRequest) {
        TaskRespons taskRespons = taskService.createTaskForUser(userId, taskRequest);
        return ResponseEntity.ok(taskRespons);
    }

    @PutMapping("/{taskId}")
    @Secured("ADMIN")
    public ResponseEntity<TaskRespons> updateTaskByTaskId(@PathVariable Long taskId,
                                                          @RequestBody TaskRequest taskRequest) {
        TaskRespons taskRespons = taskService.updateTaskByTaskId(taskId, taskRequest);
        return ResponseEntity.ok(taskRespons);
    }

    @DeleteMapping("/{taskId}")
    @Secured("ADMIN")
    public ResponseEntity<String> deleteTaskByTaskId(@PathVariable Long taskId) {
        boolean deletedTask = taskService.deleteTaskByTaskId(taskId);
        return deletedTask ? ResponseEntity.status(HttpStatus.OK).body("delete task successfully")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("task cannot be deleted");
    }

    @DeleteMapping("/{userId}/{taskId}")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseEntity<String> deleteTaskByUserIdAndTaskId(@PathVariable Long taskId,
                                                              @PathVariable Long userId) {
        boolean deletedTask = taskService.deleteTaskByUserIdAndTaskId(taskId, userId);
        return deletedTask ? ResponseEntity.status(HttpStatus.OK).body("delete task successfully")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("task cannot be deleted");
    }

}
