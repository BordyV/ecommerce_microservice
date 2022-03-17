package com.example.cart.controller;


import com.example.cart.dto.Cart;
import com.example.cart.dto.CartItem;
import com.example.cart.repository.CartItemRepository;
import com.example.cart.repository.CartRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;


    @PostMapping(value = "/cart")
    public ResponseEntity<Cart> createNewCart(@RequestBody Cart cartData)
    {
        Cart newCart = new Cart(cartData.getProducts());
        Cart cart = cartRepository.save(newCart);
        if (cart == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a new cart");
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

    @GetMapping(value = "/cart/{id}")
    public Optional<Cart> getCart(@PathVariable Long id)
    {
        Optional<Cart> cart = cartRepository.findById(id);
        if (!cart.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");
        return cart;
    }

    @PostMapping(value = "/cart/{id}")
    @Transactional
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long id, @RequestBody CartItem cartItem)
    {
        Cart cart = cartRepository.getOne(id);
        if (cart == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Couldn't get cart");

        Boolean productAlreadyExist = false;
        for (CartItem cItem: cart.getProducts()) {
            if(cItem.getProductId().equals(cartItem.getProductId())) {
                cItem.setQuantity(cItem.getQuantity()+cartItem.getQuantity());
                productAlreadyExist = true;
            }
        }

        //si le produit n'existe pas on l'ajoute
        if (!productAlreadyExist)
            cart.addProduct(cartItem);

        cart = cartRepository.save(cart);
        return new ResponseEntity<Cart>(cart, HttpStatus.CREATED);
    }

}
