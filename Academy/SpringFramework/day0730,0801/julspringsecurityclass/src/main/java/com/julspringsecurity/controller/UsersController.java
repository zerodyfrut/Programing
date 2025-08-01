package com.julspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.julspringsecurity.entity.Role;
import com.julspringsecurity.service.UserService;

@Controller
@RequestMapping("/admin")
public class UsersController {

    @Autowired
    UserService service;

    @GetMapping("/list")
    public void list(@RequestParam(value = "p", defaultValue = "0") int p, Model m) {
        m.addAttribute("page", service.findAll(p));
    }

    @PatchMapping("/update")
    public String patchupdate(@RequestParam("username") String username, @RequestParam("role") Role role, Model m) {
        service.updateRole(username, role);


        return "redirect:/admin/list";
    }

}
