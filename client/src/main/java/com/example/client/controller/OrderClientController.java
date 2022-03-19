package com.example.client.controller;

import com.example.client.bean.CartBean;
import com.example.client.bean.CartItemBean;
import com.example.client.bean.OrderBean;
import com.example.client.bean.ProductBean;
import com.example.client.proxy.MsCartProxy;
import com.example.client.proxy.MsOrderProxy;
import com.example.client.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderClientController {


    @Autowired
    private MsOrderProxy msOrderProxy;


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
    public String index(Model model) {
        List<OrderBean> orders = msOrderProxy.list();
        model.addAttribute("orders", orders);
        return "order";
    }
}
