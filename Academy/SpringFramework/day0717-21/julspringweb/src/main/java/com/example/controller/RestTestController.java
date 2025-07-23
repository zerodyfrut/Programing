package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rest")
public class RestTestController {

    @GetMapping
    public String form() {
        return "rest/form";
    }

    @PostMapping
    public String postMethodNameform(@ModelAttribute("name") String name) {
        System.out.println("Post name : " + name);
        return "rest/test";
    }

    @PutMapping()
    public String putMethodName() {
        System.out.println("Put 방식 실행 ");
        return "rest/test";
    }
    
    @DeleteMapping("/{name}")
    public String delete(@PathVariable("name") String name) {
        System.out.println("Delete 방식 실행 ");
        System.out.println("name : "+name);
        
        return "redirect:/";
    }

}
