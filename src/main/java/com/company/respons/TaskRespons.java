package com.company.respons;

import com.company.entity.Task;
import lombok.Data;

import java.sql.Time;

@Data
public class TaskRespons {
    private Long id;
    private String taskName;
    private Time deadline;
    private String text;
    private Long userId;

    public TaskRespons(Task task) {
        this.id = task.getId();
        this.taskName = task.getTaskName();
        this.deadline = task.getDedline();
        this.text = task.getText();
        this.userId = task.getUser().getId();
    }
}
