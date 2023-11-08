package com.company.request;

import com.company.dto.UserDTO;
import com.company.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Data
public class UserLoginRequest {
    private String email;
    private String password;

    public User userDTOConvertToUser(UserLoginRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }
}
