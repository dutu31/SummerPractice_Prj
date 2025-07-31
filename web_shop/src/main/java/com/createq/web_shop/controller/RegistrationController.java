package com.createq.web_shop.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.createq.web_shop.dto.UserDTO;
import com.createq.web_shop.facades.UserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserFacade userFacade;

    @Autowired
    public RegistrationController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public String redirectToForm() {
        return "redirect:/register/perform_register";
    }


    @GetMapping("/perform_register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @GetMapping(value = "/ajax/perform_register", produces = "text/html")
    @ResponseBody
    public String showRegistrationFormAjax(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping(value = "/perform_register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerForm(@Valid UserDTO userDTO,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        try {
            userFacade.registerUser(userDTO);
            return "redirect:/login?registered";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @PostMapping(value = "/perform_register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String,String>> registerAjax(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result) {

        if (result.hasErrors()) {
            String msg = result.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("message", msg));
        }
        try {
            userFacade.registerUser(userDTO);
            return ResponseEntity.ok(Collections.singletonMap("message", "OK"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(Collections.singletonMap("message", e.getMessage()));
        }
    }

}
