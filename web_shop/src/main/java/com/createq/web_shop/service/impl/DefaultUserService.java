package com.createq.web_shop.service.impl;

import com.createq.web_shop.converter.UserConverter;
import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.model.User;
import com.createq.web_shop.repository.UserRepository;
import com.createq.web_shop.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Transactional
public class DefaultUserService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    @Autowired
    public DefaultUserService(UserRepository user, PasswordEncoder passwordEncoder, UserConverter userConverter) {
        this.userRepository = user;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User findbyMail(String username) {
        return userRepository.findByMail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        try {
            User existingUser=findByUsername(userDTO.getUsername());
            if(existingUser!=null) {
                throw new IllegalArgumentException("Username already exists!");
            }
        } catch(UsernameNotFoundException e) {

        }
        try {
            User existingEmailUser=findbyMail(userDTO.getMail());
            if(existingEmailUser!=null) {
                throw new IllegalArgumentException("Email already exists!");
            }
        } catch(UsernameNotFoundException e) {

        }

        User user=userConverter.convert(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser=userRepository.save(user);
        return userConverter.convertToDTO(savedUser);

    }



}



