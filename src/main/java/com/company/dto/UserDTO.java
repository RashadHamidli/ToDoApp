package com.company.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private List<TaskDTO> taskList=new ArrayList<>();
}
