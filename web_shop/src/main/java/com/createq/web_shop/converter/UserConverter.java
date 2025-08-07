package com.createq.web_shop.converter;

import ch.qos.logback.core.model.Model;
import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User convert (UserDTO userDTO) {
        User user=new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setMail(userDTO.getMail());
        user.setRole("user");
        return user;

    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setMail(user.getMail());
        return userDTO;
    }

}
