package com.createq.web_shop.controller;

import com.createq.web_shop.dto.CartDTO;
import com.createq.web_shop.dto.CartItemDTO;
import com.createq.web_shop.facades.CartFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartFacade cartFacade;


    public CartController(CartFacade cartFacade) {
        this.cartFacade = cartFacade;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<CartDTO> getCart(Principal principal) {
        if (principal == null) {
            return ResponseEntity.ok(new CartDTO()); // empty cart for non users
        }
        CartDTO cartDTO = cartFacade.getCart(principal.getName());
        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<CartDTO> addItem(@RequestBody CartItemDTO itemDTO, Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        CartDTO updated = cartFacade.addItem(principal.getName(), itemDTO);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/merge")
    @ResponseBody
    public ResponseEntity<CartDTO> mergeCart(@RequestBody List<CartItemDTO> items, Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        CartDTO merged = cartFacade.mergeCart(principal.getName(), items);
        return ResponseEntity.ok(merged);
    }

    @PostMapping("/clear")
    @ResponseBody
    public ResponseEntity<CartDTO> clearCart(Principal principal) {
        if (principal == null) {
            return ResponseEntity.badRequest().build();
        }
        CartDTO empty = cartFacade.clearCart(principal.getName());
        return ResponseEntity.ok(empty);
    }

    @GetMapping("/page")
    public String getCartPage() {
        return "cart";
    }
}
