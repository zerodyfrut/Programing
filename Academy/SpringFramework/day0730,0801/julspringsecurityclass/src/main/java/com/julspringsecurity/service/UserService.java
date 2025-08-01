package com.julspringsecurity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.julspringsecurity.dao.UsersRepository;
import com.julspringsecurity.entity.Role;
import com.julspringsecurity.entity.Users;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	UsersRepository dao;

	@Autowired
	PasswordEncoder encoder;

	public Page<Users> findAll(int page) {
		Pageable paging = PageRequest.of(page, 10);
		return dao.findAll(paging);
	}

	public Optional<Users> findOne(String username) {
		return dao.findById(username);
	}

	public void insertUser(Users user) {
		// 암호화(BCrypt 알고리즘 )
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_MEMBER);
		user.setEnabled('T');
		dao.save(user);

	}
	@Transactional
	public void  updateRole(String username, Role role){
		Users user = dao.findById(username).get();
		user.setRole(role);
	}


}
