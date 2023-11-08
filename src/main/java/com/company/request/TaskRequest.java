package com.company.request;

import com.company.respons.TaskRespons;
import lombok.Data;

import java.sql.Time;

@Data
public class TaskRequest {
    private Long id;
    private String taskName;
    private Time deadline;
    private String text;
    private Long userId;

    public TaskRequest(TaskRespons task) {
        this.id = task.getId();
        this.taskName = task.getTaskName();
        this.deadline = task.getDeadline();
        this.text = task.getText();
        this.userId = task.getUserId();
    }
}
