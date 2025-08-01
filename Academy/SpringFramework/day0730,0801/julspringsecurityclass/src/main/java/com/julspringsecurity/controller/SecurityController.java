package com.julspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.julspringsecurity.auth.SecurityUser;
import com.julspringsecurity.entity.Users;
import com.julspringsecurity.service.UserService;

@Controller
public class SecurityController {

	@Autowired
	UserService service;

	@GetMapping("/")
	public String index() {
		System.out.println("index 요청입니다.");
		return "index";
	}

	@GetMapping("/member")
	public void forMember() {
		System.out.println("Member 요청입니다.");
	}

	@GetMapping("/manager")
	public void forManager() {
		System.out.println("Manager 요청입니다.");
	}

	@GetMapping("/admin")
	public void forAdmin(@AuthenticationPrincipal SecurityUser users) {
		System.out.println(users.getUsername());
		System.out.println(users.getUsers());
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

	@PostMapping("/insert")
	public String insert(Users users) {
		service.insertUser(users);

		return "redirect:/";
	}
}
