package com.example.order.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long cartId;


    public Order(){}

    public Order(Long id, Long cartId){
        this.id = id;
        this.cartId = cartId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
