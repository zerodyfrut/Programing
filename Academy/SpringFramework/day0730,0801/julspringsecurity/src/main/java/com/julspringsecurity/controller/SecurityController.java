package com.julspringsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.julspringsecurity.dao.UsersRepository;
import com.julspringsecurity.entity.Users;
import com.julspringsecurity.service.UserService;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SecurityController {
    @Autowired
    UserService service;
    @Autowired
    UsersRepository dao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/member")
    public void forMember() {
        System.out.println("Member 요청");
    }

    @GetMapping("/manager")
    public void forManager() {
        System.out.println("Manager 요청");
    }

    @GetMapping("/admin")
    public void foradmin() {
        System.out.println("admin 요청");
    }

    @GetMapping("/login")
    public void login() {
    }

    @GetMapping("/loginSuccess")
    public void loginSuccess() {
    }

    @GetMapping("/accessDenied")
    public void accessDenied() {
    }

    @GetMapping("/insert")
    public void insert() {
    }

     @GetMapping("/delete")
    public void delete() {
    }

    @PostMapping("/insert")
    public String insert(Users users) {
        service.insertUser(users);

        return "redirect:/";
    }

    @GetMapping("/list")
    public void updateUser(Model m, Users users) {
        List userList = dao.findAll();
        m.addAttribute("userList", userList);
    }

    @GetMapping("/update/{username}")
    public String updateUser(@PathVariable String username, Model m) {
        Users user = dao.findById(username).orElseThrow();
        m.addAttribute("user", user);

        return "/update";
    }

    @PostMapping("/update")
    public String updateUser(Users fixUser) {
        Users user = dao.findById(fixUser.getUsername()).orElseThrow();
        if(fixUser.getName()==null){
        user.setName(fixUser.getName());
        }
        if (fixUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(fixUser.getPassword())); // ✅ 인코딩 필수
        }
        user.setRole(fixUser.getRole());

        dao.save(user);

        return "redirect:/list";
    }



}
