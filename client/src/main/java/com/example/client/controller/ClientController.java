package com.example.client.controller;

import com.example.client.bean.ProductBean;
import com.example.client.proxy.MsCartProxy;
import com.example.client.proxy.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    @Autowired
    private MsProductProxy msProductProxy;

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
}
