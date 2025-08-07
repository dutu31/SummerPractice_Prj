package com.createq.web_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.facades.UserFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserFacade userFacade;

    @Autowired
    public AdminController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public String showAdminPage(Model model) {
        model.addAttribute("users", userFacade.getAllUsers());
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("roles", List.of("user", "admin"));
        return "adminPage";
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO savedUser = userFacade.registerUser(userDTO);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userFacade.deleteUserById(id);
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404)
                    .body(Map.of("message", "User not found with id: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("message", "Internal server error"));
        }
    }

    @PostMapping("/promote/{id}")
    public ResponseEntity<?> promoteUser(@PathVariable Long id) {
        try {
            userFacade.updateUserRole(id, "admin");
            return ResponseEntity.ok().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body("User not found with id: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error");
        }
    }
}
