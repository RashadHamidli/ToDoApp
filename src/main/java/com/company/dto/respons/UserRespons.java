package com.company.dto.respons;

import com.company.dao.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserRespons {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private List<TaskRespons> taskList;

    public UserRespons(User user, List<TaskRespons> tasks) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.taskList = tasks;
    }
}
