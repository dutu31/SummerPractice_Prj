package com.createq.web_shop.converter;

import com.createq.web_shop.dto.CartDTO;
import com.createq.web_shop.dto.CartItemDTO;
import com.createq.web_shop.model.Cart;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CartConverter {
    private static final double DELIVERY_FEE = 25.00;
    private final CartItemConverter itemConverter;

    public CartConverter(CartItemConverter itemConverter) {
        this.itemConverter = itemConverter;
    }

    public CartDTO convert(Cart cart) {
        CartDTO dto=new CartDTO();
        dto.setUsername(cart.getUser().getUsername());
        List<CartItemDTO> items = cart.getItems().stream()
                .map(itemConverter::convertItem)
                .collect(Collectors.toList());
        dto.setItems(items);

        double subtotal = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        dto.setSubtotal(subtotal);
        dto.setDeliveryFee(DELIVERY_FEE);
        dto.setTotal(subtotal+DELIVERY_FEE);
        return dto;
    }

    public List<CartDTO> convertAll(List<Cart> carts) {
        return carts.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }


}
