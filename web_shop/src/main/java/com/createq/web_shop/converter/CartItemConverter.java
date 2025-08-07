package com.createq.web_shop.converter;

import com.createq.web_shop.dto.CartItemDTO;
import com.createq.web_shop.model.Cart;
import com.createq.web_shop.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemConverter {
   public CartItemDTO convertItem(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setProductId(item.getProductId());
        dto.setTitle(item.getTitle());
        dto.setPrice(item.getPrice());
        dto.setQuantity(item.getQuantity());
        dto.setImageUrl(item.getImageUrl());
        return dto;
    }

    public CartItem toEntity(CartItemDTO dto, Cart cart) {
        CartItem item = new CartItem();
        item.setProductId(dto.getProductId());
        item.setTitle(dto.getTitle());
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());
        item.setImageUrl(dto.getImageUrl());
        item.setCart(cart);
        return item;
    }

}
