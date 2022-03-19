package com.example.client.controller;

import com.example.client.bean.*;
import com.example.client.proxy.MsOrderProxy;
import com.example.client.proxy.MsProductProxy;
import com.example.client.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderClientController {


    @Autowired
    private MsOrderProxy msOrderProxy;

    @Autowired
    private MsProductProxy msProductProxy;


    @PostMapping(value = "/order/{idCart}/totalPrice/{totalPrice}")
    public ResponseEntity createNewOrder(@PathVariable String idCart, @PathVariable String totalPrice) {
        OrderBean orderBean = new OrderBean(Long.parseLong(idCart), Double.parseDouble(totalPrice));
        return msOrderProxy.addOrder(orderBean);
    }

    @GetMapping("/orders")
    public String orderList(Model model) {
        List<OrderBean> orders = msOrderProxy.list();
        model.addAttribute("orders", orders);
        return "order";
    }

    @GetMapping("/order/{idOrder}")
    public String index(Model model, @PathVariable String idOrder) {

        Optional<OrderBean> order = Optional.of(new OrderBean());
        List<ProductBean> orderProducts = new ArrayList<>();

        if(idOrder != null && !idOrder.equals("null") && NumberUtils.isNumeric(idOrder)) {
            try {
                order = msOrderProxy.getOrder(Long.parseLong(idOrder));

                if(order.isPresent()) {
                    for (OrderItemBean orderItemBean: order.get().getProducts()) {
                        Optional<ProductBean> productBean =  msProductProxy.get(orderItemBean.getProductId());
                        productBean.ifPresent(orderProducts::add);
                    }
                }
            } catch (Exception e ) {
                //c'est moche mais pas le temps pour régler l'exception du orderProxy.getCart
                model.addAttribute("cart", order.get());
                model.addAttribute("cartProducts", orderProducts);
                return "orderDetail";
            }
        }
        model.addAttribute("order", order.get());
        model.addAttribute("orderProducts", orderProducts);
        return "orderDetail";
    }
}
