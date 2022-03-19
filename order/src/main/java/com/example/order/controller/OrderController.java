package com.example.order.controller;

import com.example.order.dto.CartBean;
import com.example.order.dto.CartItemBean;
import com.example.order.dto.Order;
import com.example.order.proxy.MsCartProxy;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MsCartProxy msCartProxy;

    @GetMapping(value = "/orders")
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @GetMapping(value = "/order/{id}")
    public Optional<Order> getOrder(@PathVariable Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (!order.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Cannot get order");
        return order;
    }

    @PostMapping(value = "/order")
    public ResponseEntity<Order> createNewOrder(@RequestBody Order orderData) {
        Order order = new Order();

        try {
            Optional<CartBean> cartBean = msCartProxy.getCart(orderData.getCartId());
            if(cartBean.isPresent()) {
                boolean isDelete = msCartProxy.deleteCart(orderData.getCartId());
                if(isDelete) {
                    order.setCartId(orderData.getCartId());
                    order.setTotalPrice(orderData.getTotalPrice());
                    order.setProducts(cartBean.get().getProductsAsOrderItem());
                    order = orderRepository.save(order);
                }
            }
        } catch (Exception e ){
            return new ResponseEntity<Order>(new Order(), HttpStatus.FORBIDDEN);
        }

        if(order.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot create order");
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

}
