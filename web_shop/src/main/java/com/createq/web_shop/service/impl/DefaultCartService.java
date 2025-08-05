package com.createq.web_shop.service.impl;

import com.createq.web_shop.model.Cart;
import com.createq.web_shop.model.CartItem;
import com.createq.web_shop.model.User;
import com.createq.web_shop.repository.CartRepository;
import com.createq.web_shop.repository.UserRepository;
import com.createq.web_shop.service.CartService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DefaultCartService implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;


    @Autowired
    public DefaultCartService(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Cart getCartForUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }

    @Override
    public void addItem(String username, CartItem item) {
        Cart cart = getCartForUsername(username);

        CartItem existing = cart.getItems().stream()
                .filter(ci -> ci.getProductId().equals(item.getProductId()))
                .findFirst()
                .orElse(null);

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + item.getQuantity());
            existing.setTitle(item.getTitle());
            existing.setPrice(item.getPrice());
            existing.setImageUrl(item.getImageUrl());
        } else {
            item.setCart(cart);
            cart.getItems().add(item);
        }

        saveCart(cart);

    }

    @Override
    public void clearCart(String username) {
        Cart cart = getCartForUsername(username);
        cart.getItems().clear();
        saveCart(cart);

    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }
}
