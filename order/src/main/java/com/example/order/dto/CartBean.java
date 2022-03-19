package com.example.order.dto;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
    private Long id;
    private List<CartItemBean> products;

    public CartBean() {

    }
    public CartBean(Long id, List<CartItemBean> products) {
        this.id = id;
        this.products = products;
    }

    public CartBean(List<CartItemBean> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemBean> getProducts() {
        return products;
    }

    public void setProducts(List<CartItemBean> products) {
        this.products = products;
    }

    public List<OrderItem> getProductsAsOrderItem() {
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItemBean cartItemBean:this.products) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItemBean.getProductId());
            orderItem.setQuantity(cartItemBean.getQuantity());
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
