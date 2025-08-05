package com.createq.web_shop.facades.impl;

import com.createq.web_shop.converter.CartConverter;
import com.createq.web_shop.converter.CartItemConverter;
import com.createq.web_shop.dto.CartDTO;
import com.createq.web_shop.dto.CartItemDTO;
import com.createq.web_shop.facades.CartFacade;
import com.createq.web_shop.model.Cart;
import com.createq.web_shop.model.CartItem;
import com.createq.web_shop.service.CartService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultCartFacade implements CartFacade {
    private final CartService cartService;
    private final CartConverter cartConverter;
    private final CartItemConverter itemConverter;

    public DefaultCartFacade(CartService cartService, CartConverter cartConverter, CartItemConverter itemConverter) {
        this.cartService = cartService;
        this.cartConverter = cartConverter;
        this.itemConverter = itemConverter;
    }

    @Override
    public CartDTO getCart(String username) {
        Cart cart=cartService.getCartForUsername(username);
        return cartConverter.convert(cart);
    }

    @Override
    public CartDTO addItem(String username, CartItemDTO itemDTO) {
        Cart cart = cartService.getCartForUsername(username);
        CartItem item = itemConverter.toEntity(itemDTO, cart);
        cartService.addItem(username, item);
        return cartConverter.convert(cartService.getCartForUsername(username));

    }

    @Override
    public CartDTO clearCart(String username) {
        cartService.clearCart(username);
        Cart empty=cartService.getCartForUsername(username);
        return cartConverter.convert(empty);
    }

    @Override
    public CartDTO mergeCart(String username, List<CartItemDTO> items) {
        Cart cart = cartService.getCartForUsername(username);

        for (CartItemDTO dto : items) {
            CartItem existing = cart.getItems().stream()
                    .filter(ci -> ci.getProductId().equals(dto.getProductId()))
                    .findFirst()
                    .orElse(null);

            if (existing != null) {
                existing.setQuantity(existing.getQuantity() + dto.getQuantity());
                existing.setTitle(dto.getTitle());
                existing.setPrice(dto.getPrice());
                existing.setImageUrl(dto.getImageUrl());
            } else {
                CartItem item = itemConverter.toEntity(dto, cart);
                cart.getItems().add(item);
            }
        }

        cartService.saveCart(cart);

        return cartConverter.convert(cart);
    }


}
