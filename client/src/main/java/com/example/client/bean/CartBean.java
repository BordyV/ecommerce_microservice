package com.example.client.bean;

import java.util.List;

public class CartBean {
    private Long id;
    private List<CartItemBean> products;

    public CartBean(Long id, List<com.example.client.bean.CartItemBean> products) {
        this.id = id;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<com.example.client.bean.CartItemBean> getProducts() {
        return products;
    }

    public void setProducts(List<com.example.client.bean.CartItemBean> products) {
        this.products = products;
    }
}
