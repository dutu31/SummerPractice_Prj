package com.createq.web_shop.service;

import com.createq.web_shop.model.Cart;
import com.createq.web_shop.model.CartItem;

public interface CartService {
    Cart getCartForUsername(String username);
    void addItem(String username, CartItem item);
    void clearCart(String username);
    void saveCart(Cart cart);
}
