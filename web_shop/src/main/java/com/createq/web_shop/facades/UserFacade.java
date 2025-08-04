package com.createq.web_shop.facades;

import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.model.User;

import java.util.List;

public interface UserFacade {
    UserDTO registerUser(UserDTO userDTO);
    List<User> getAllUsers();
    void deleteUserById(Long id);
    void updateUserRole(Long id, String role);
    User convertAndRegisterUser(UserDTO userDTO, String role);
}
