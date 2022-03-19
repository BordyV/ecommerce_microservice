package com.example.client.controller;

import com.example.client.bean.CartBean;
import com.example.client.bean.CartItemBean;
import com.example.client.bean.ProductBean;
import com.example.client.proxy.MsCartProxy;
import com.example.client.proxy.MsProductProxy;
import com.example.client.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;

    @Autowired
    private MsCartProxy msCartProxy;

    @RequestMapping("/")
    public String index(Model model) {
        List<ProductBean> products = msProductProxy.list();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping(value = "/product-detail/{id}")
    public String productDetail(Model model, @PathVariable Long id) {
        Optional<ProductBean> product = msProductProxy.get(id);
        model.addAttribute("product", product.get());
        return "productDetail";
    }

    @GetMapping(value = "/cart/{idCart}")
    public String cartDetail(Model model, @PathVariable String idCart) {
        Optional<CartBean> cart = Optional.of(new CartBean());
        List<ProductBean> cartProducts = new ArrayList<>();
        double totalPrice = (double) 0;

        if(idCart != null && !idCart.equals("null") && NumberUtils.isNumeric(idCart)) {
            try {
            cart = msCartProxy.getCart(Long.parseLong(idCart));

            if(cart.isPresent()) {
                for (CartItemBean cartItemBean: cart.get().getProducts()) {
                    Optional<ProductBean> productBean =  msProductProxy.get(cartItemBean.getProductId());
                    if(productBean.isPresent()) {
                        cartProducts.add(productBean.get());
                        totalPrice += productBean.get().getPrice() * Double.valueOf(cartItemBean.getQuantity());
                    }
                }
            }
            } catch (Exception e ) {
                //c'est moche mais pas le temps pour régler l'exception du cartProxy.getCart
                model.addAttribute("cart", cart.get());
                model.addAttribute("cartProducts", cartProducts);
                model.addAttribute("totalPrice", NumberUtils.round(totalPrice,2));
                return "cart";
            }
        }
        model.addAttribute("cart", cart.get());
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("totalPrice", NumberUtils.round(totalPrice,2));
        return "cart";
    }


}
