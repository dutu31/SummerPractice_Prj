package com.createq.web_shop.facades;

import com.createq.web_shop.dto.CartDTO;
import com.createq.web_shop.dto.CartItemDTO;

import java.util.List;

public interface CartFacade {
    CartDTO getCart(String username);
    CartDTO addItem(String username, CartItemDTO itemDTO);
    CartDTO clearCart(String username);
    CartDTO mergeCart(String username, List<CartItemDTO> items);
}
