package com.company.service.inter;

import com.company.dto.request.TaskRequest;
import com.company.dto.response.TaskRespons;

import java.util.List;

public interface TaskService {
    List<TaskRespons> getAllTasks();

    TaskRespons getTaskById(Long taskId);

    TaskRespons createTask(TaskRequest taskRequest);

    TaskRespons createTaskForUser(Long userId, TaskRequest taskRequest);

    TaskRespons updateTaskByTaskId(Long taskId, TaskRequest taskRequest);

    boolean deleteTaskByTaskId(Long taskId);

    boolean deleteTaskByUserIdAndTaskId(Long taskId, Long userId);

}
