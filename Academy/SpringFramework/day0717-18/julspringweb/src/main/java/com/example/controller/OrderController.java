
package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.command.OrderCommand;


@Controller
public class OrderController {

    @GetMapping("/order/order")
    public String form(){
        return "order/orderForm";
    }

    @PostMapping("/order/order")
    public String submit(@ModelAttribute("order") OrderCommand command){
        return "/order/submit";
    }
    
}
