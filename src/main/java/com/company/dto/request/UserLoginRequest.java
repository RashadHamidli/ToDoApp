package com.company.dto.request;

import com.company.dao.entity.User;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UserLoginRequest {
    private String email;
    private String password;

    public User userLoginRequestConvertToUser(UserLoginRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
}
