package com.example.client.controller;

import com.example.client.bean.CartBean;
import com.example.client.bean.CartItemBean;
import com.example.client.proxy.MsCartProxy;
import com.example.client.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartClientController {

    @Autowired
    private MsCartProxy msCartProxy;

    @PostMapping(value = "/cart/{idCart}/product/{idProduct}")
    public ResponseEntity addToCart(@PathVariable String idCart, @PathVariable String idProduct) {

        CartItemBean cartItemBean = new CartItemBean(Long.parseLong(idProduct), 1);

        //si le panier existe dans l'html
        if(idCart != null && !idCart.equals("null") && NumberUtils.isNumeric(idCart)) {
            return msCartProxy.addProductToCart(Long.parseLong(idCart), cartItemBean );
        } else {
            List<CartItemBean> cartItemList = new ArrayList();
            cartItemList.add(cartItemBean);
            CartBean cartBean = new CartBean(cartItemList);

            return msCartProxy.createNewCart(cartBean);
        }
    }
}
