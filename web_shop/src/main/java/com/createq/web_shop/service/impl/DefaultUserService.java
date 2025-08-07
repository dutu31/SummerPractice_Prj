package com.createq.web_shop.service.impl;

import com.createq.web_shop.converter.UserConverter;
import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.model.User;
import com.createq.web_shop.repository.UserRepository;
import com.createq.web_shop.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserRole(Long id, String role) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setRole(role);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User not found with ID: " + id);
        }
    }

    @Override
    public User convertAndRegisterUser(UserDTO userDTO, String role) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        }
        if (userRepository.findByMail(userDTO.getMail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        User user = userConverter.convert(userDTO);
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Loading user: " + username);
        User user = findByUsername(username);
        System.out.println("User found: " + user.getUsername() + ", role: " + user.getRole());
        String role="ROLE_" + user.getRole().toUpperCase();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }
}



