package com.createq.web_shop.controller;

import com.createq.web_shop.dto.CartDTO;
import com.createq.web_shop.dto.CartItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
public class CartController {
    @GetMapping("/cart")
    public String getCartPage() {
        return "cart";
    }




}
