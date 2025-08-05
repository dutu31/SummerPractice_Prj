package com.createq.web_shop.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;


@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping("/user/status")
    @ResponseBody
    public Map<String, Boolean> userStatus(Principal principal, HttpServletRequest request) {
        Map<String, Boolean> result = new HashMap<>();
        boolean authenticated = (principal != null);
        result.put("authenticated", authenticated);
        return result;
    }

}
