package com.createq.web_shop.repository;

import com.createq.web_shop.model.Cart;
import com.createq.web_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
