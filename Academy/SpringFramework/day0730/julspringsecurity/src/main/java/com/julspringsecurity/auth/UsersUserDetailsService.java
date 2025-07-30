package com.julspringsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.julspringsecurity.dao.UsersRepository;
import com.julspringsecurity.entity.Users;

@Service
public class UsersUserDetailsService implements UserDetailsService {// 반드시 구현 필요
    @Autowired
    private UsersRepository usersRepository;
    // form에 입력한 ID(username)값으로 DB에서 데이터 가져옴

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username).orElse(new Users());
        // username으로 Users 가져오고 없으면 새로운(빈) Users 객체 생성 및 반환
        if (user.getUsername() == null) {
            throw new UsernameNotFoundException(username + " 사용자 없음");
        } else {
            return new SecurityUser(user);
        }

    }
}
