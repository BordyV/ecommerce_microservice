package com.example.client.bean;

public class OrderBean {

    private Long id;
    private Long cartId;

    public OrderBean(Long id, Long cartId) {
        this.id = id;
        this.cartId = cartId;
    }

    public Long getId() {
        return id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}
