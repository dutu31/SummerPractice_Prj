package com.createq.web_shop.facades.impl;

import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.facades.UserFacade;
import com.createq.web_shop.service.impl.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserFacade implements UserFacade {

    private final DefaultUserService userService;

    @Autowired
    public DefaultUserFacade(DefaultUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        try {
            return userService.registerUser(userDTO);
        }
        catch (Exception e) {
            throw new RuntimeException("Registration failed",e);
        }
    }
}
