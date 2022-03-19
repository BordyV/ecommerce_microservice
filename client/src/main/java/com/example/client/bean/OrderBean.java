package com.example.client.bean;

import java.util.List;

public class OrderBean {

    private Long id;
    private Long cartId;
    private double totalPrice;
    private List<OrderItemBean> products;


    public OrderBean(Long cartId) {
        this.cartId = cartId;
    }

    public OrderBean(Long cartId, double totalPrice) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
    }

    public OrderBean(Long id, Long cartId, double totalPrice) {
        this.id = id;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
    }

    public OrderBean() {
    }

    public OrderBean(Long cartId, double totalPrice, List<OrderItemBean> products) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public OrderBean(Long id, Long cartId, double totalPrice, List<OrderItemBean> products) {
        this.id = id;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.products = products;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemBean> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItemBean> products) {
        this.products = products;
    }
}
