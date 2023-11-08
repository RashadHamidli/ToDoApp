package com.company.controller;

import com.company.dto.TaskDTO;
import com.company.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRestController {

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        // Tüm görevleri getirme işlemi
        return taskService.getAllTasks();
    }

    @GetMapping("/{taskId}")
    public TaskDTO getTaskById(@PathVariable Long taskId) {
        // Belirli bir görevi ID'ye göre getirme işlemi
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/user/{userId}")
    public TaskDTO createTaskForUser(@PathVariable Long userId, @RequestBody TaskDTO taskDTO) {
        // Belirli bir kullanıcı için yeni bir görev oluşturma işlemi
        return taskService.createTaskForUser(userId, taskDTO);
    }

    @PutMapping("/{taskId}")
    public TaskDTO updateTask(@PathVariable Long taskId, @RequestBody TaskDTO taskDTO) {
        // Görev güncelleme işlemi
        return taskService.updateTask(taskId, taskDTO);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        // Görev silme işlemi
        taskService.deleteTask(taskId);
    }

}
