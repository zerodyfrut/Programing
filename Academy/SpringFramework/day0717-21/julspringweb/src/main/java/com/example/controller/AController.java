package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AController {    

    @GetMapping("/a")
    public String a(){
        return "a/a";
    }
}
