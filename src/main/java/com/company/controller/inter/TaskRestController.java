package com.company.controller.inter;

import com.company.dto.request.TaskRequest;
import com.company.dto.response.TaskRespons;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface TaskRestController {
    ResponseEntity<List<TaskRespons>> getAllTasks();

    ResponseEntity<TaskRespons> getTaskByTaskId(@PathVariable Long taskId);

    ResponseEntity<TaskRespons> createTask(@RequestBody TaskRequest taskRequest);

    ResponseEntity<TaskRespons> createTaskForUserId(@PathVariable Long userId,
                                                    @RequestBody TaskRequest taskRequest);

    ResponseEntity<TaskRespons> updateTaskByTaskId(@PathVariable Long taskId,
                                                   @RequestBody TaskRequest taskRequest);

    ResponseEntity<String> deleteTaskByTaskId(@PathVariable Long taskId);

    ResponseEntity<String> deleteTaskByUserIdAndTaskId(@PathVariable Long taskId,
                                                       @PathVariable Long userId);

}
