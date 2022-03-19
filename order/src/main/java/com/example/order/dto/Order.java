package com.example.order.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="OrderTable")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long cartId;
    private double totalPrice;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> products;

    public Order(){}

    public Order(Long cartId, double totalPrice, List<OrderItem> products) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public Order(Long id, Long cartId, double totalPrice,  List<OrderItem> products){
        this.id = id;
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.products = products;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }
}
