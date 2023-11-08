package com.company.mapper;

import com.company.dto.TaskDTO;
import com.company.entity.Task;
import com.company.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDTO taskConvertToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setUserId(task.getId());
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setDeadline(task.getDedline());
        taskDTO.setText(task.getText());
        return taskDTO;
    }

    public Task taskDTOConvertToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTaskName(taskDTO.getTaskName());
        task.setDedline(taskDTO.getDeadline());
        task.setText(taskDTO.getText());
        User user = new User();
        user.setId(taskDTO.getId());
        task.setUser(user);
        return task;
    }
}
