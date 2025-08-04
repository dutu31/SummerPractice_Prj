package com.createq.web_shop.service;

import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User findbyMail(String username);
    UserDTO registerUser(UserDTO userDTO);
    List<User> getAllUsers();
    void deleteUserById(Long id);
    void updateUserRole(Long id, String role);
    User convertAndRegisterUser(UserDTO userDTO, String role);
}
