package com.example.client.controller;

import com.example.client.bean.ProductBean;
import com.example.client.proxy.MsProductProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
