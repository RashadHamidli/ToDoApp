package com.company.request;

import com.company.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;

    public User userRequestConvertToUser(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.name);
        user.setSurname(userRequest.surname);
        user.setEmail(userRequest.email);
        user.setPassword(userRequest.password);
        return user;
    }
}
