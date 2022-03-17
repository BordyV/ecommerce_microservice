package com.example.order.controller;

import com.example.order.dto.Order;
import com.example.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping(value = "/orders")
    public List<Order> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders;
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
        Order newOrder = new Order(orderData.getCartId());
        Order order = orderRepository.save(newOrder);

        if(order.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot create order");

        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

}
