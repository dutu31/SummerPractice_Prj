package com.createq.web_shop.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy="cart", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<CartItem> items = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }
}
