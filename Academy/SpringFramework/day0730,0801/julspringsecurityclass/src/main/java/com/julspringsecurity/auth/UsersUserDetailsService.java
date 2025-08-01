package com.julspringsecurity.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.julspringsecurity.dao.UsersRepository;
import com.julspringsecurity.entity.Users;


@Service
public class UsersUserDetailsService implements UserDetailsService{
@Autowired
	private UsersRepository usersRepository;

//form에 입력한 ID(username)값으로 DB에서 데이터 가져옴

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  Users user = usersRepository.findByUsername(username).orElse(new Users());
		if (user.getUsername() == null) {
			throw new UsernameNotFoundException(username + " 사용자 없음");
		} else {
			return new SecurityUser(user);
		}
    }
}
