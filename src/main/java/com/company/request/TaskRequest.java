package com.company.request;

import com.company.entity.Task;
import com.company.respons.TaskRespons;
import lombok.Data;

import java.sql.Time;

@Data
public class TaskRequest {
    private String taskName;
    private Time deadline;
    private String text;
    private Long userId;

    public Task taskRequestConverToTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTaskName(taskRequest.taskName);
        task.setDedline(taskRequest.deadline);
        task.setText(taskRequest.text);
        return task;
    }
}
