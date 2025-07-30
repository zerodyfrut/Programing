package com.julspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.julspringsecurity.dao.UsersRepository;
import com.julspringsecurity.entity.Role;
import com.julspringsecurity.entity.Users;

@Service
public class UserService {
    @Autowired
    UsersRepository dao;
    @Autowired
    PasswordEncoder encoder;

    public void insertUser(Users user) {
        // 암호화(BCrypt 알고리즘)
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_MEMBER);
        user.setEnabled('T');

        dao.save(user);
    }

}
