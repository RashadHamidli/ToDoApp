package com.company.dto.request;

import com.company.dao.entities.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserRequest {
    private String name;
    private String surname;
    private String email;
    private String password;

    public User userRequestConvertToUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.name);
        user.setLastName(userRequest.surname);
        user.setEmail(userRequest.email);
        user.setPassword(userRequest.password);
        return user;
    }
}
