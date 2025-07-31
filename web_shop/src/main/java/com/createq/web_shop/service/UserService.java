package com.createq.web_shop.service;

import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.model.User;

public interface UserService {
    User findByUsername(String username);
    User findbyMail(String username);
    UserDTO registerUser(UserDTO userDTO);
}
