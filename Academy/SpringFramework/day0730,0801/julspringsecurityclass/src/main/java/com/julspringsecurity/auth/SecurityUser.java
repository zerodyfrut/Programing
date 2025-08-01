package com.julspringsecurity.auth;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.julspringsecurity.entity.Users;

//전달받은 Users객체안에 있는 비번과 form에 입력한 비번이 같은지 비교 
//같으면 시큐리티 세션안에 SecurityUser객체 저장 후 로그인 성공
//다르면 에러 발생(다시 로그인 form로 가기)
public class SecurityUser extends User {
	private static final long serialVersionUID = 1L;

	private Users users;
	
	public SecurityUser(Users users) {
		super(users.getUsername(), users.getPassword(),
				AuthorityUtils.createAuthorityList(users.getRole().toString()));
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Users getUsers() {
		return users;
	}
	
}
