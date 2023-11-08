package com.company.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Time;


@Component
@RequiredArgsConstructor
@Data
public class TaskDTO {
    private Long id;
    private String taskName;
    private Time deadline;
    private String text;
    private Long userId;
}
